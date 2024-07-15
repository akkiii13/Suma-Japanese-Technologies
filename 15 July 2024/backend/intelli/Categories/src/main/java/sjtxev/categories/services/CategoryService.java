package sjtxev.categories.services;

import java.util.List;

import jakarta.validation.Valid;
import sjtxev.categories.payloads.CategoryDto;

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

	// check whether the category exists by category id
	Boolean existsByCategoryId(Long categoryId);

	// get list of category id's by category name
	List<Long> getListOfCategoryIdsByCategoryName(String categoryName);

}
