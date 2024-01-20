package sjtxev.products.controllers;

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

import jakarta.validation.Valid;
import sjtxev.products.configurations.AllConstants;
import sjtxev.products.payloads.ProductDto;
import sjtxev.products.responseObjects.ApiResponse;
import sjtxev.products.responseObjects.ProductResponse;
import sjtxev.products.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ProductsController {

	@Autowired
	private ProductService productService;

	// save new product
	@PostMapping("/save")
	public ResponseEntity<ProductDto> addNewProduct(@Valid @RequestBody ProductDto productDto) {
		return new ResponseEntity<ProductDto>(this.productService.addNewProduct(productDto), HttpStatus.CREATED);
	}

	// update product by product id
	@PutMapping("/update/{productId}")
	public ResponseEntity<ProductDto> updateProductByProductId(@Valid @RequestBody ProductDto productDto,
			@PathVariable("productId") Long productId) {
		return new ResponseEntity<ProductDto>(this.productService.updateProductByProductId(productDto, productId),
				HttpStatus.OK);
	}

	// delete single product by product id
	@DeleteMapping("/delete/deleteSingleProductByProductId/{productId}")
	public ResponseEntity<ApiResponse> deleteSingleProductByProductId(@PathVariable("productId") Long pId) {
		this.productService.deleteSingleProductByProductId(pId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully", true), HttpStatus.OK);
	}

	// delete list of products by category id
	@DeleteMapping("/delete/deleteListOfProductsByCategoryId/{categoryId}")
	public ResponseEntity<ApiResponse> deleteListOfProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
		this.productService.deleteListOfProductsByCategoryId(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("List of products deleted successfully", true),
				HttpStatus.OK);
	}

	// delete all products
	@DeleteMapping("/delete/deleteAllProducts")
	public ResponseEntity<ApiResponse> deleteAllProducts() {
		this.productService.deleteAllProducts();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All products deleted successfully", true),
				HttpStatus.OK);
	}

	// get single product by product id
	@GetMapping("/getSingleProductByProductId/{productId}")
	public ResponseEntity<ProductDto> getSingleProductByProductId(@PathVariable("productId") Long pId) {
		return new ResponseEntity<ProductDto>(this.productService.getSingleProductByProductId(pId), HttpStatus.OK);
	}

	// get list of products by any value
	@GetMapping("/getListOfProductsByAnyValue/{searchString}")
	public ResponseEntity<Collection<ProductDto>> findListOfProductsByNumericValue(
			@PathVariable("searchString") String searchString) {
		List<ProductDto> findByAnyValue1 = new ArrayList<>();
		List<ProductDto> findByAnyValue2 = new ArrayList<>();
		List<ProductDto> findByAnyValue3 = new ArrayList<>();
		if (searchString.matches("[0-9]+")) {
			findByAnyValue1 = this.productService.findListOfProductsByNumericValue(Long.parseLong(searchString),
					Double.parseDouble(searchString));
		}
		if (searchString.equals("true") || searchString.equals("false")) {
			findByAnyValue2 = this.productService.findListOfProductsByBooleanValue(Boolean.parseBoolean(searchString));
		}
		findByAnyValue3 = this.productService.findListOfProductsByStringValue(searchString, searchString, searchString,
				searchString);
		List<ProductDto> newList = Stream.of(findByAnyValue1, findByAnyValue2, findByAnyValue3)
				.flatMap(Collection<ProductDto>::stream).distinct().toList();
		Collection<ProductDto> nonDuplicatedProducts = newList.stream()
				.<Map<Long, ProductDto>>collect(HashMap::new, (m, e) -> m.put(e.getProductId(), e), Map::putAll)
				.values();
		return new ResponseEntity<Collection<ProductDto>>(nonDuplicatedProducts, HttpStatus.OK);
	}

	// get all products
	@GetMapping("/getAllProducts")
	public ResponseEntity<ProductResponse> getAllProducts(
			@RequestParam(value = "pageNumber", defaultValue = AllConstants.PRODUCTS_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AllConstants.PRODUCTS_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AllConstants.PRODUCTS_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AllConstants.PRODUCTS_SORT_DIRECTION, required = false) String sortDirection) {
		ProductResponse allProducts = this.productService.getAllProducts(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<ProductResponse>(allProducts, HttpStatus.OK);
	}

	// get list of products by category id
	@GetMapping("/getListOfProductsByCategoryId/{categoryId}")
	public ResponseEntity<List<ProductDto>> getListOfProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
		List<ProductDto> productsByCategoryId = this.productService.getListOfProductsByCategoryId(categoryId);
		return new ResponseEntity<List<ProductDto>>(productsByCategoryId, HttpStatus.OK);
	}

	// get list of products by category name
	@GetMapping("/getListOfProductsByCategoryName/{categoryName}")
	public ResponseEntity<List<ProductDto>> getListOfProductsByCategoryName(
			@PathVariable("categoryName") String categoryName) {
		List<ProductDto> productsByCategoryName = this.productService.getListOfProductsByCategoryName(categoryName);
		return new ResponseEntity<List<ProductDto>>(productsByCategoryName, HttpStatus.OK);
	}

	// search list of products by keyword
	@GetMapping("/searchListOfProducts/{keyword}")
	public ResponseEntity<List<ProductDto>> searchListOfProducts(@PathVariable("keyword") String keyword) {
		List<ProductDto> productsDtos = this.productService.searchListOfProducts(keyword);
		return new ResponseEntity<List<ProductDto>>(productsDtos, HttpStatus.OK);
	}

	// check whether the product is present or not by given product id
	@GetMapping("/get/checkWhetherTheProductExistsOrNotByProductId/{productId}")
	public Boolean existsSingleProductByProductIdOrNot(@PathVariable("productId") Long productId) {
		return this.productService.existsSingleProductByProductIdOrNot(productId);
	}

}
