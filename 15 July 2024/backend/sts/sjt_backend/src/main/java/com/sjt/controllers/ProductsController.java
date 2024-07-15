package com.sjt.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjt.config.AllConstants;
import com.sjt.payloads.ProductsDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.responseObjects.ProductResponse;
import com.sjt.services.ProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
@Validated
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	public ProductsController(ProductsService productsService) {
		super();
	}

	// save new product
	@PostMapping("/save")
	public ResponseEntity<ProductsDto> addNewProduct(@Valid @RequestBody ProductsDto productDto) {
		return new ResponseEntity<ProductsDto>(this.productsService.addNewProduct(productDto), HttpStatus.CREATED);
	}

	// update product by product id
	@PutMapping("/update/{productId}")
	public ResponseEntity<ProductsDto> updateProductByProductId(@Valid @RequestBody ProductsDto productDto,
			@PathVariable("productId") Long productId) {
		return new ResponseEntity<ProductsDto>(this.productsService.updateProductByProductId(productDto, productId),
				HttpStatus.OK);
	}

	// delete single product by product id
	@DeleteMapping("/delete/deleteProductByProductId/{productId}")
	public ResponseEntity<ApiResponse> deleteProductByProductId(@PathVariable("productId") Long pId) {
		this.productsService.deleteProductByProductId(pId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully", true), HttpStatus.OK);
	}

	// get single product by product id
	@GetMapping("/getSingleProductByProductId/{productId}")
	public ResponseEntity<ProductsDto> getSingleProductByProductId(@PathVariable("productId") Long pId) {
		return new ResponseEntity<ProductsDto>(this.productsService.getSingleProductByProductId(pId), HttpStatus.OK);
	}

	// get list of products by any value
	@GetMapping("/getListOfProductsByAnyValue/{searchString}")
	public ResponseEntity<Collection<ProductsDto>> findListOfProductsByNumericValue(
			@PathVariable("searchString") String searchString) {
		List<ProductsDto> findByAnyValue1 = new ArrayList<>();
		List<ProductsDto> findByAnyValue2 = new ArrayList<>();
		List<ProductsDto> findByAnyValue3 = new ArrayList<>();
		if (searchString.matches("[0-9]+")) {
			findByAnyValue1 = this.productsService.findListOfProductsByNumericValue(Long.parseLong(searchString),
					Double.parseDouble(searchString));
		}
		if (searchString.equals("true") || searchString.equals("false")) {
			findByAnyValue2 = this.productsService.findListOfProductsByBooleanValue(Boolean.parseBoolean(searchString));
		}
		findByAnyValue3 = this.productsService.findListOfProductsByStringValue(searchString, searchString, searchString,
				searchString);
		List<ProductsDto> newList = Stream.of(findByAnyValue1, findByAnyValue2, findByAnyValue3)
				.flatMap(Collection<ProductsDto>::stream).distinct().toList();
		Collection<ProductsDto> nonDuplicatedProducts = newList.stream()
				.<Map<Long, ProductsDto>>collect(HashMap::new, (m, e) -> m.put(e.getProductId(), e), Map::putAll)
				.values();
		return new ResponseEntity<Collection<ProductsDto>>(nonDuplicatedProducts, HttpStatus.OK);
	}

	// get all products
	@GetMapping("/getAllProducts")
	public ResponseEntity<ProductResponse> getAllProducts(
			@RequestParam(value = "pageNumber", defaultValue = AllConstants.PRODUCTS_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AllConstants.PRODUCTS_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AllConstants.PRODUCTS_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AllConstants.PRODUCTS_SORT_DIRECTION, required = false) String sortDirection) {
		ProductResponse allProducts = this.productsService.getAllProducts(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<ProductResponse>(allProducts, HttpStatus.OK);
	}

	// get list of products by category id
	@GetMapping("/getListOfProductsByCategoryId/{categoryId}")
	public ResponseEntity<List<ProductsDto>> getListOfProductsByCategoryId(
			@PathVariable("categoryId") Long categoryId) {
		List<ProductsDto> productsByCategoryId = this.productsService.getListOfProductsByCategoryId(categoryId);
		return new ResponseEntity<List<ProductsDto>>(productsByCategoryId, HttpStatus.OK);
	}

	// get list of products by category name
	@GetMapping("/getListOfProductsByCategoryName/{categoryName}")
	public ResponseEntity<List<ProductsDto>> getListOfProductsByCategoryName(
			@PathVariable("categoryName") String categoryName) {
		List<ProductsDto> productsByCategoryName = this.productsService.getListOfProductsByCategoryName(categoryName);
		return new ResponseEntity<List<ProductsDto>>(productsByCategoryName, HttpStatus.OK);
	}

	// search list of products by keyword
	@GetMapping("/searchListOfProducts/{keyword}")
	public ResponseEntity<List<ProductsDto>> searchListOfProducts(@PathVariable("keyword") String keyword) {
		List<ProductsDto> productsDtos = this.productsService.searchListOfProducts(keyword);
		return new ResponseEntity<List<ProductsDto>>(productsDtos, HttpStatus.OK);
	}

}
