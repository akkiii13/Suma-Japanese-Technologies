package com.sjt.services;

import java.util.List;

import com.sjt.payloads.CategoryDto;

import jakarta.validation.Valid;

public interface CategoryService {

	// add category
	CategoryDto addCategory(@Valid CategoryDto categoryDto);

	// update category by category id
	CategoryDto updateCategoryByCategoryId(@Valid CategoryDto categoryDto, Long categoryId);

	// delete single category by category id
	void deletSingleCategoryByCategoryId(Long categoryId);

	// get single category by category id
	CategoryDto getSingleCategoryByCategoryId(Long categoryId);

	// get list of categories by category name
	List<CategoryDto> getListOfCategoriesByCategoryName(String categoryName);

	// get list of categories by any value
	List<CategoryDto> getListOfCategoriesByAnyValue(Long categoryId, String searchString);

	// get all categories
	List<CategoryDto> getAllCategories();

}
