package sjtxev.productsImages.services.implementations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.transaction.Transactional;
import sjtxev.productsImages.entities.ProductImage;
import sjtxev.productsImages.entities.entityHelper.ProductImagePrimaryKey;
import sjtxev.productsImages.exceptions.ResourceNotFoundException;
import sjtxev.productsImages.payloads.ProductImageDto;
import sjtxev.productsImages.repositories.ProductImageRepository;
import sjtxev.productsImages.services.ProductImageService;
import sjtxev.productsImages.services.ProductsClient;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10)
@Service
@Transactional
public class ProductImageServiceImplementation implements ProductImageService {

	@Autowired
	ProductImageRepository productImageRepository;

	@Autowired
	ProductsClient productsClient;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// upload multiple images
	@Override
	public void uploadImage(MultipartFile[] files, Long productId) throws IOException {

		if (productId == null)
			throw new IllegalArgumentException("Product ID must be provided.");

		Boolean isOrNot = this.productsClient.existsSingleProductByProductIdOrNot(productId);

		if (!isOrNot)
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		ProductImage productImage = new ProductImage();

		Integer countOfFiles = 0;

		if (files == null || files.length == 0)
			throw new IllegalArgumentException("You must select at least one file for uploading");

		for (int i = 0; i < files.length; i++, countOfFiles++) {

			String originalFilename = files[i].getOriginalFilename();

			String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));

			if (!(fileFormat.equalsIgnoreCase(".jpeg") || fileFormat.equalsIgnoreCase(".png")
					|| fileFormat.equalsIgnoreCase(".jpg")))
				throw new IllegalArgumentException("File format is not supported");

			Long maxProductImageId = productImageRepository.findMaxProductImageIdByProductId(productId);
			Long nextProductImageId = (maxProductImageId != null) ? maxProductImageId + 1 : 1;

//			String name = "productId_" + String.valueOf(productId) + "_productImageId_"
//					+ String.valueOf(nextProductImageId) + "_____" + originalFilename;

			ProductImagePrimaryKey productImagePrimaryKey = new ProductImagePrimaryKey();
			productImagePrimaryKey.setProductImageIdPK(nextProductImageId);
			productImagePrimaryKey.setProductIdPK(productId);

			productImage.setProductImageId(productImagePrimaryKey);
			productImage.setName(originalFilename);
			productImage.setType(files[i].getContentType());
			productImage.setImageData(this.compressImage(files[i].getBytes()));
			productImage.setProductId(productId);

			productImageRepository.save(productImage);

		}

	}

	// delete productImage by productImage id and product id
	@Override
	public void deleteProductImageByProductImageIdAndProductId(Long productImageId, Long productId) {

		if (productImageId == null)
			throw new IllegalArgumentException("ProductImage id cannot be null");

		if (productId == null)
			throw new IllegalArgumentException("Product id cannot be null");

		Boolean isOrNot = this.productsClient.existsSingleProductByProductIdOrNot(productId);

		if (isOrNot)
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		ProductImagePrimaryKey productImagePrimaryKey = new ProductImagePrimaryKey();
		productImagePrimaryKey.setProductImageIdPK(productImageId);
		productImagePrimaryKey.setProductIdPK(productId);

		ProductImage productImage = this.productImageRepository.findById(productImagePrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("ProductImage", "productImage id",
						String.valueOf(productImageId) + " and product id : " + Long.toString(productId)));

		this.productImageRepository.delete(productImage);

	}

	// delete list of productImage by product id
	@Override
	public void deleteListOfProductImagesByProductId(Long productId) {

		if (productId == null)
			throw new IllegalArgumentException("Product id cannot be null");

		List<ProductImage> productImagelist = this.productImageRepository.findByProductId(productId);

		if (productImagelist.isEmpty())
			throw new ResourceNotFoundException("ProductImage", "product id", String.valueOf(productId));

		productImagelist.stream().forEach(i -> this.productImageRepository.delete(i));

	}

	// delete list of product images by given product id's list
	@Override
	public void deleteTheListOfProductImagesByGivenProductIdList(List<Long> productIds) {

		productIds.stream().forEach(i -> this.productImageRepository.deleteByProductId(i));

	}

	// delete all products images
	@Override
	public void deleteAll() {

		this.productImageRepository.deleteAll();

	}

	// download list of images by name
	@Override
	public List<ProductImageDto> downloadListOfImagesByName(String name) {

		if (name.isBlank())
			throw new IllegalArgumentException("name cannot be blank");

		List<ProductImage> productImage = productImageRepository.findByNameContaining(name);

		if (productImage.isEmpty())
			throw new IllegalArgumentException("Image not found with name : " + name);

		List<ProductImageDto> collect = productImage.stream().map(this::mapToProductImageDto)
				.collect(Collectors.toList());

		collect.stream().forEach(i -> i.setImageData(this.decompressImage(i.getImageData())));

		return collect;

	}

	// get single image by productImage id and product id
	@Override
	public ProductImageDto getSingleImageByProductImageIdAndProductId(Long productImageId, Long productId) {

		if (productImageId == null)
			throw new IllegalArgumentException("ProductImage id cannot be null");

		if (productId == null)
			throw new IllegalArgumentException("Product id cannot be null");

		ProductImagePrimaryKey productImagePrimaryKey = new ProductImagePrimaryKey();
		productImagePrimaryKey.setProductImageIdPK(productImageId);
		productImagePrimaryKey.setProductIdPK(productId);

		ProductImage productImage = this.productImageRepository.findById(productImagePrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("ProductImage", "productImage id",
						String.valueOf(productImageId) + " and product id : " + Long.toString(productId)));

		ProductImageDto mapToProductImageDto = this.mapToProductImageDto(productImage);

		mapToProductImageDto.setImageData(this.decompressImage(mapToProductImageDto.getImageData()));

		return mapToProductImageDto;

	}

	// get list of images by product id
	@Override
	public List<ProductImageDto> getListOfImagesByProductId(Long productId) {

		if (productId == null)
			throw new IllegalArgumentException("Product is not found with product id : " + productId);

		Boolean isOrNot = this.productsClient.existsSingleProductByProductIdOrNot(productId);

		if (isOrNot)
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		List<ProductImage> findByProducts = this.productImageRepository.findByProductId(productId);

		if (findByProducts.isEmpty())
			throw new ResourceNotFoundException("ProductImage", "product id", Long.toString(productId));

		return findByProducts.stream().map(this::mapToProductImageDto).collect(Collectors.toList());
	}

	private byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}
		try {
			outputStream.close();
		} catch (Exception e) {
		}
		return outputStream.toByteArray();
	}

	private byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
			outputStream.close();
		} catch (Exception e) {
		}
		return outputStream.toByteArray();
	}

	private ProductImageDto mapToProductImageDto(ProductImage productImage) {
		return this.modelMapper.map(productImage, ProductImageDto.class);
	}

//	private ProductImage mapToProductImage(ProductImageDto productImageDto) {
//		return this.modelMapper.map(productImageDto, ProductImage.class);
//	}

}
