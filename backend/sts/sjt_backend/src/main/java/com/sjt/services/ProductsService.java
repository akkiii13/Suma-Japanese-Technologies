package com.sjt.services;

import java.util.List;

import com.sjt.payloads.ProductsDto;
import com.sjt.responseObjects.ProductResponse;

import jakarta.validation.Valid;

public interface ProductsService {

	// save new product
	ProductsDto addNewProduct(@Valid ProductsDto productDto);

	// update product by product id
	ProductsDto updateProductByProductId(@Valid ProductsDto productDto, Long productId);

	// delete single product by product id
	void deleteProductByProductId(Long productId);

	// get single product by product id
	ProductsDto getSingleProductByProductId(Long productId);

	// find list of products products by numeric value
	List<ProductsDto> findListOfProductsByNumericValue(Long productId, Double price);

	// find list of products products by boolean value
	List<ProductsDto> findListOfProductsByBooleanValue(Boolean availability);

	// find list of products products by string value
	List<ProductsDto> findListOfProductsByStringValue(String brandName, String modelName, String photos,
			String description);

	// get all products
	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	// get list of products by category id
	List<ProductsDto> getListOfProductsByCategoryId(Long id);

	// get list of products by category name
	List<ProductsDto> getListOfProductsByCategoryName(String categoryName);

	// search list of products by keyword
	List<ProductsDto> searchListOfProducts(String keyword);

}
