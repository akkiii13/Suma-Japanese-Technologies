package com.sjt.services.implementations;

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

import com.sjt.entities.Category;
import com.sjt.entities.Products;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.ProductsDto;
import com.sjt.repositories.CategoryRepository;
import com.sjt.repositories.ProductsRepository;
import com.sjt.responseObjects.ProductResponse;
import com.sjt.services.ProductsService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ModelMapper modelMapper;

	// save new product
	@Override
	public ProductsDto addNewProduct(@Valid ProductsDto productDto) {

		Long categoryId = productDto.getCategory().getCategoryId();

		this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", Long.toString(categoryId)));

		Products product = this.mapToProducts(productDto);

		Products addProduct = this.productsRepository.save(product);

		return this.mapToProductsDto(addProduct);

	}

	// update product by product id
	@Override
	public ProductsDto updateProductByProductId(@Valid ProductsDto productDto, Long productId) {

		Long categoryId = productDto.getCategory().getCategoryId();

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		Products product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "payment id", Long.toString(productId)));

		this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", Long.toString(categoryId)));

		product.setBrandName(productDto.getBrandName());
		product.setModelName(productDto.getModelName());
		product.setPrice(productDto.getPrice());
//		product.setPhotos(productDto.getPhotos());
		product.setDescription(productDto.getDescription());
		product.setAvailability(productDto.getAvailability());

		Products updatedProduct = this.productsRepository.save(product);

		return this.mapToProductsDto(updatedProduct);

	}

	// delete product by product id
	@Override
	public void deleteProductByProductId(Long productId) {

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		Products product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product id", Long.toString(productId)));

		this.productsRepository.delete(product);

	}

	// get single product by product id
	@Override
	public ProductsDto getSingleProductByProductId(Long productId) {

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		Products product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product id", Long.toString(productId)));

		return this.mapToProductsDto(product);

	}

	// find list of products products by numeric value
	@Override
	public List<ProductsDto> findListOfProductsByNumericValue(Long productId, Double price) {

		if (productId == null)
			throw new IllegalArgumentException("Product id must not null");

		if (price == null)
			throw new IllegalArgumentException("Price must not null");

		List<Products> products = this.productsRepository.findByProductIdOrPrice(productId, price);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "value", String.valueOf(price));

		return products.stream().map(this::mapToProductsDto).collect(Collectors.toList());

	}

	// find list of products products by boolean value
	@Override
	public List<ProductsDto> findListOfProductsByBooleanValue(Boolean availability) {

		List<Products> products = this.productsRepository.findByAvailability(availability);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "value", String.valueOf(availability));

		List<ProductsDto> productsDto = products.stream().map(this::mapToProductsDto).collect(Collectors.toList());

		return productsDto;

	}

	// find list of products products by string value
	@Override
	public List<ProductsDto> findListOfProductsByStringValue(String brandName, String modelName, String photos,
			String description) {

		if (brandName.isBlank())
			throw new IllegalArgumentException("String cannot be blank");

		List<Products> products = this.productsRepository
				.findByBrandNameContainingOrModelNameContainingOrDescriptionContainingAllIgnoringCase(brandName,
						modelName, description);

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "value", brandName);

		return products.stream().map(this::mapToProductsDto).collect(Collectors.toList());

	}

	// get all products
	@Override
	public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = sortDirection.equals("ascending") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Products> pageProduct = this.productsRepository.findAll(pageable);

		List<Products> products = pageProduct.getContent();

		if (products.isEmpty())
			throw new ResourceNotFoundException("Products", "", "");

		List<ProductsDto> collectProducts = products.stream().map(this::mapToProductsDto).collect(Collectors.toList());

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
	public List<ProductsDto> getListOfProductsByCategoryId(Long categoryId) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id cannot be null");

		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("Category", "category id", String.valueOf(categoryId)));

		List<Products> findByCategory = this.productsRepository.findByCategory(category);

		if (findByCategory.isEmpty())
			throw new ResourceNotFoundException("Products", "", "");

		return findByCategory.stream().map(this::mapToProductsDto).collect(Collectors.toList());

	}

	// get list of products by category name
	@Override
	public List<ProductsDto> getListOfProductsByCategoryName(String categoryName) {

		if (categoryName.isBlank())
			throw new IllegalArgumentException("Category name cannot be blank");

		List<Category> categories = this.categoryRepository.findByCategoryNameContainingIgnoreCase(categoryName);

		if (categories.isEmpty())
			throw new ResourceNotFoundException("Category", "category name", categoryName);

		List<ProductsDto> productsDtoList = new ArrayList<>();

		for (Category category : categories) {

			List<Products> products = this.productsRepository.findByCategory(category);

			if (!products.isEmpty())
				productsDtoList.addAll(products.stream().map(this::mapToProductsDto).collect(Collectors.toList()));

		}

		if (productsDtoList.isEmpty())
			throw new ResourceNotFoundException("Products", "category name", categoryName);

		return productsDtoList;

	}

	// search list of products by keyword
	@Override
	public List<ProductsDto> searchListOfProducts(String keyword) {

		if (keyword.isBlank())
			throw new IllegalArgumentException("Keyword cannot be blank");

		List<Products> products = this.productsRepository
				.findByBrandNameContainingOrModelNameContainingOrDescriptionContainingAllIgnoringCase(keyword, keyword,
						keyword);

		List<ProductsDto> productsDto = products.stream().map(this::mapToProductsDto).collect(Collectors.toList());

		return productsDto;

	}

	private ProductsDto mapToProductsDto(Products products) {
		return this.modelMapper.map(products, ProductsDto.class);
	}

	private Products mapToProducts(ProductsDto productsDto) {
		return this.modelMapper.map(productsDto, Products.class);
	}

}
