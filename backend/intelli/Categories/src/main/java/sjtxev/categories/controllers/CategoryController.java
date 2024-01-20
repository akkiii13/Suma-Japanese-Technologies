package sjtxev.categories.controllers;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sjtxev.categories.payloads.CategoryDto;
import sjtxev.categories.responseObjects.ApiResponse;
import sjtxev.categories.services.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	// add category
	@PostMapping("/save")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.addCategory(categoryDto));
	}

	// update category by category id
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategoryByCategoryId(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") Long cId) {
		return ResponseEntity.ok(this.categoryService.updateCategoryByCategoryId(categoryDto, cId));
	}

	// delete single category by category id
	@DeleteMapping("/delete/deletSingleCategoryByCategoryId/{categoryId}")
	public ResponseEntity<ApiResponse> deletSingleCategoryByCategoryId(@PathVariable("categoryId") Long cId) {
		this.categoryService.deletSingleCategoryByCategoryId(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}

	// get single category by category id
	@GetMapping("/get/getSingleCategoryByCategoryId/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategoryByCategoryId(@PathVariable("categoryId") Long cId) {
		return ResponseEntity.ok(this.categoryService.getSingleCategoryByCategoryId(cId));
	}

	// get list of categories by category name
	@GetMapping("/get/getListOfCategoriesByCategoryName/{categoryName}")
	public ResponseEntity<List<CategoryDto>> getListOfCategoriesByCategoryName(
			@PathVariable("categoryName") String cName) {
		return ResponseEntity.ok(this.categoryService.getListOfCategoriesByCategoryName(cName));
	}

	// get list of categories by any value
	@GetMapping("/get/getListOfCategoriesByAnyValue/{searchString}")
	public ResponseEntity<List<CategoryDto>> getListOfCategoriesByAnyValue(
			@PathVariable("searchString") String searchString) {
		List<CategoryDto> singleCategoryByAnyValue1 = new ArrayList<>();
		List<CategoryDto> singleCategoryByAnyValue2 = new ArrayList<>();
		if (searchString.matches("[0-9]+")) {
			singleCategoryByAnyValue1 = this.categoryService.getListOfCategoriesByAnyValue(Long.parseLong(searchString),
					searchString);
		} else {
			singleCategoryByAnyValue2 = this.categoryService.getListOfCategoriesByAnyValue(0L, searchString);
		}
		List<CategoryDto> newList = Stream
				.concat(singleCategoryByAnyValue1.stream(), singleCategoryByAnyValue2.stream()).toList();
		return ResponseEntity.ok(newList);
	}

	// get all categories
	@GetMapping("/get/getAllCategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}

	// check whether the category exists by category id
	@GetMapping("/get/existsByCategoryId/{categoryId}")
	public Boolean existsByCategoryId(@PathVariable("categoryId") Long categoryId) {
		return this.categoryService.existsByCategoryId(categoryId);
	}

	// get list of category id's by category name
	@GetMapping("/get/getListOfCategoryIdsByCategoryName/{categoryName}")
	public List<Long> getListOfCategoryIdsByCategoryName(@PathVariable("categoryName") String categoryName) {
		return this.categoryService.getListOfCategoryIdsByCategoryName(categoryName);
	}

}
