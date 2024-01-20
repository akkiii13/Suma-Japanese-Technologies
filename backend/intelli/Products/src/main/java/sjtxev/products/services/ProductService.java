package sjtxev.products.services;

import java.util.List;

import sjtxev.products.payloads.ProductDto;
import sjtxev.products.responseObjects.ProductResponse;

import jakarta.validation.Valid;

public interface ProductService {

	// save new product
	ProductDto addNewProduct(@Valid ProductDto productDto);

	// update product by product id
	ProductDto updateProductByProductId(@Valid ProductDto productDto, Long productId);

	// delete single product by product id
	void deleteSingleProductByProductId(Long productId);

	// delete list of products by category id
	void deleteListOfProductsByCategoryId(Long categoryId);

	// delete all products
	void deleteAllProducts();

	// get single product by product id
	ProductDto getSingleProductByProductId(Long productId);

	// find list of products products by numeric value
	List<ProductDto> findListOfProductsByNumericValue(Long productId, Double price);

	// find list of products products by boolean value
	List<ProductDto> findListOfProductsByBooleanValue(Boolean availability);

	// find list of products products by string value
	List<ProductDto> findListOfProductsByStringValue(String brandName, String modelName, String photos,
			String description);

	// get all products
	ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	// get list of products by category id
	List<ProductDto> getListOfProductsByCategoryId(Long id);

	// get list of products by category name
	List<ProductDto> getListOfProductsByCategoryName(String categoryName);

	// search list of products by keyword
	List<ProductDto> searchListOfProducts(String keyword);

	// check whether the product is present or not by given product id
	Boolean existsSingleProductByProductIdOrNot(Long productId);

}
