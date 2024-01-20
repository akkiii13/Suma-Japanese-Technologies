package com.sjt.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjt.entities.Category;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.CategoryDto;
import com.sjt.repositories.CategoryRepository;
import com.sjt.services.CategoryService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ModelMapper modelMapper;

	// add category
	@Override
	public CategoryDto addCategory(@Valid CategoryDto categoryDto) {

		Category category = this.mapToCategory(categoryDto);

		Category savedCategory = this.categoryRepository.save(category);

		return this.mapToCategoryDto(savedCategory);

	}

	// update category by category id
	@Override
	public CategoryDto updateCategoryByCategoryId(@Valid CategoryDto categoryDto, Long categoryId) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id must not be null");

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", Long.toString(categoryId)));

		category.setCategoryId(categoryId);
		category.setCategoryName(categoryDto.getCategoryName());

		Category updatedCategory = this.categoryRepository.save(category);

		return this.mapToCategoryDto(updatedCategory);

	}

	// delete single category by category id
	@Override
	public void deletSingleCategoryByCategoryId(Long categoryId) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id must not be null");

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", Long.toString(categoryId)));

		this.categoryRepository.delete(category);

	}

	// get single category by category id
	@Override
	public CategoryDto getSingleCategoryByCategoryId(Long categoryId) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id must not be null");

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", Long.toString(categoryId)));

		return this.mapToCategoryDto(category);

	}

	// get list of categories by category name
	@Override
	public List<CategoryDto> getListOfCategoriesByCategoryName(String categoryName) {

		if (categoryName.isBlank())
			throw new IllegalArgumentException("Category name must not be null");

		List<Category> categoryList = this.categoryRepository.findByCategoryNameContainingIgnoreCase(categoryName);

		if (categoryList.isEmpty())
			throw new ResourceNotFoundException("Categories", "category name", categoryName);

		return categoryList.stream().map(this::mapToCategoryDto).collect(Collectors.toList());

	}

	// get list of categories by any value
	@Override
	public List<CategoryDto> getListOfCategoriesByAnyValue(Long categoryId, String categoryName) {

		if (categoryId == null)
			throw new IllegalArgumentException("Category id must not be null");

		if (categoryName.isBlank())
			throw new IllegalArgumentException("Category name must not be null");

		List<Category> categories = this.categoryRepository
				.findByCategoryIdOrCategoryNameContainingAllIgnoreCase(categoryId, categoryName);

		if (categories.isEmpty())
			throw new ResourceNotFoundException("Categories", "value", categoryName);

		return categories.stream().map(this::mapToCategoryDto).collect(Collectors.toList());

	}

	// get all categories
	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> categories = this.categoryRepository.findAll();

		if (categories.isEmpty())
			throw new ResourceNotFoundException("Categories", "", "");

		return categories.stream().map(this::mapToCategoryDto).collect(Collectors.toList());

	}

	private CategoryDto mapToCategoryDto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
	}

	private Category mapToCategory(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
	}

}
