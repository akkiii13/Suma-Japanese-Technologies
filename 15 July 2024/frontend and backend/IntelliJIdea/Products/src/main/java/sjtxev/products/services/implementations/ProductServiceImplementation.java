package sjtxev.products.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sjtxev.products.entities.Product;
import sjtxev.products.exceptions.ResourceNotFoundException;
import sjtxev.products.payloads.ProductDto;
//import sjtxev.products.repositories.CategoryRepository;
import sjtxev.products.repositories.ProductsRepository;
import sjtxev.products.responseObjects.ProductResponse;
import sjtxev.products.services.CategoriesClient;
import sjtxev.products.services.ProductService;
import sjtxev.products.services.ProductsImagesClient;

@Service
@Transactional
public class ProductServiceImplementation implements ProductService {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	CategoriesClient categoriesClient;

	@Autowired
	ProductsImagesClient productsImagesClient;

	@Autowired
	ModelMapper modelMapper;

	// save new product
	@Override
	public ProductDto addNewProduct(@Valid ProductDto productDto) {

		Long categoryId = productDto.getCategoryId();

		Boolean existsByCategoryId = this.categoriesClient.existsByCategoryId(categoryId);

		System.out.println(existsByCategoryId);

		if (!existsByCategoryId)
			throw new ResourceNotFoundException("Category", "category id", Long.toString(categoryId));

		Product product = this.mapToProduct(productDto);

		Product addProduct = this.productsRepository.save(product);

		return this.mapToProductDto(addProduct);

	}

	// update product by product id
	@Override
	public ProductDto updateProductByProductId(@Valid ProductDto productDto, Long productId) {

//		Long categoryId = productDto.getCategoryId();

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		Product product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "payment id", Long.toString(productId)));

//		Boolean existsByCategoryId = this.categoriesClient.existsByCategoryId(categoryId);
//
//		if (!existsByCategoryId)
//			new ResourceNotFoundException("Category", "category id", Long.toString(categoryId));

		if (productDto.getBrandName() != null)
			product.setBrandName(productDto.getBrandName());
		if (productDto.getModelName() != null)
			product.setModelName(productDto.getModelName());
		if (productDto.getPrice() != null)
			product.setPrice(productDto.getPrice());
//		if (productDto.getPhotos() != null)
//			product.setPhotos(productDto.getPhotos());
		if (productDto.getDescription() != null)
			product.setDescription(productDto.getDescription());
		if (productDto.getAvailability() != null)
			product.setAvailability(productDto.getAvailability());
		if (productDto.getCategoryId() != null)
			product.setCategoryId(productDto.getCategoryId());

		Product updatedProduct = this.productsRepository.save(product);

		return this.mapToProductDto(updatedProduct);

	}

	// delete product by product id
	@Override
	public void deleteSingleProductByProductId(Long productId) {

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		Product product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product id", Long.toString(productId)));

		this.productsImagesClient.delteProductImagesByProductId(productId);

		this.productsRepository.delete(product);

	}

	// delete list of products by category id
	@Override
	public void deleteListOfProductsByCategoryId(Long categoryId) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id must not null");

		List<Product> products = this.productsRepository.findByCategoryId(categoryId);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "category id", String.valueOf(categoryId));

		this.productsImagesClient.deleteTheListOfProductImagesByGivenProductIdList(
				products.stream().map(i -> i.getProductId()).collect(Collectors.toList()));
		products.stream().forEach(i -> this.productsRepository.delete(i));

	}

	// delete all products
	@Override
	public void deleteAllProducts() {

		this.productsImagesClient.deleteAllProductsImages();
		this.productsRepository.deleteAll();

	}

	// get single product by product id
	@Override
	public ProductDto getSingleProductByProductId(Long productId) {

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		Product product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product id", Long.toString(productId)));

		return this.mapToProductDto(product);

	}

	// find list of products products by numeric value
	@Override
	public List<ProductDto> findListOfProductsByNumericValue(Long productId, Double price) {

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		if (price == null)
			throw new IllegalArgumentException("Price must not null");

		List<Product> products = this.productsRepository.findByProductIdOrPrice(productId, price);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "value", String.valueOf(price));

		return products.stream().map(this::mapToProductDto).collect(Collectors.toList());

	}

	// find list of products products by boolean value
	@Override
	public List<ProductDto> findListOfProductsByBooleanValue(Boolean availability) {

		List<Product> products = this.productsRepository.findByAvailability(availability);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "value", String.valueOf(availability));

		List<ProductDto> productsDto = products.stream().map(this::mapToProductDto).collect(Collectors.toList());

		return productsDto;

	}

	// find list of products products by string value
	@Override
	public List<ProductDto> findListOfProductsByStringValue(String brandName, String modelName, String photos,
			String description) {

		if (brandName.isBlank())
			throw new IllegalArgumentException("String cannot be blank");

		List<Product> products = this.productsRepository
				.findByBrandNameContainingOrModelNameContainingOrDescriptionContainingAllIgnoringCase(brandName,
						modelName, description);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "value", brandName);

		return products.stream().map(this::mapToProductDto).collect(Collectors.toList());

	}

	// get all products
	@Override
	public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = sortDirection.equals("ascending") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Product> pageProduct = this.productsRepository.findAll(pageable);

		List<Product> products = pageProduct.getContent();

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "", "");

		List<ProductDto> collectProducts = products.stream().map(this::mapToProductDto).collect(Collectors.toList());

		ProductResponse productResponse = new ProductResponse();
		productResponse.setPageNumber(pageProduct.getNumber());
		productResponse.setPageSize(pageProduct.getSize());
		productResponse.setProductsDto(collectProducts);
		productResponse.setTotalElements(pageProduct.getTotalElements());
		productResponse.setTotalPages(pageProduct.getTotalPages());
		productResponse.setLastPage(pageProduct.isLast());

		return productResponse;

	}

	// get list of products by category id
	@Override
	public List<ProductDto> getListOfProductsByCategoryId(Long categoryId) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id cannot be null");

		Boolean existsByCategoryId = this.categoriesClient.existsByCategoryId(categoryId);

		if (!existsByCategoryId)
			new ResourceNotFoundException("Category", "category id", Long.toString(categoryId));

		List<Product> findByCategory = this.productsRepository.findByCategoryId(categoryId);

		if (findByCategory.isEmpty())
			throw new ResourceNotFoundException("Products", "category id", String.valueOf(categoryId));

		return findByCategory.stream().map(this::mapToProductDto).collect(Collectors.toList());

	}

	// get list of products by category name
	@Override
	public List<ProductDto> getListOfProductsByCategoryName(String categoryName) {

		if (categoryName.isBlank())
			throw new IllegalArgumentException("Category name cannot be blank");

		List<Long> categoryIds = this.categoriesClient.findByCategoryName(categoryName);

		if (categoryIds.isEmpty())
			throw new ResourceNotFoundException("Category", "category name", categoryName);

		List<ProductDto> productsDtoList = new ArrayList<>();

		for (Long category : categoryIds) {

			List<Product> products = this.productsRepository.findByCategoryId(category);

			if (!products.isEmpty())
				productsDtoList.addAll(products.stream().map(this::mapToProductDto).collect(Collectors.toList()));

		}

		if (productsDtoList.isEmpty())
			throw new ResourceNotFoundException("Products", "category name", categoryName);

		return productsDtoList;

	}

	// search list of products by keyword
	@Override
	public List<ProductDto> searchListOfProducts(String keyword) {

		if (keyword.isBlank())
			throw new IllegalArgumentException("Keyword cannot be blank");

		List<Product> products = this.productsRepository
				.findByBrandNameContainingOrModelNameContainingOrDescriptionContainingAllIgnoringCase(keyword, keyword,
						keyword);

		List<ProductDto> productsDto = products.stream().map(this::mapToProductDto).collect(Collectors.toList());

		return productsDto;

	}

	// check whether the product is present or not by given product id
	@Override
	public Boolean existsSingleProductByProductIdOrNot(Long productId) {

		return this.productsRepository.existsById(productId);

	}

	private ProductDto mapToProductDto(Product product) {
		return this.modelMapper.map(product, ProductDto.class);
	}

	private Product mapToProduct(ProductDto productDto) {
		return this.modelMapper.map(productDto, Product.class);
	}

}
