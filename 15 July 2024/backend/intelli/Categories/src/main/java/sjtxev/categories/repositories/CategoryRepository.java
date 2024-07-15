package sjtxev.categories.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sjtxev.categories.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	// find list of categories by category name while ignoring case
	List<Category> findByCategoryNameContainingIgnoreCase(String categoryName);

	// find list of categories by any value
	List<Category> findByCategoryIdOrCategoryNameContainingAllIgnoreCase(Long categoryId, String CategoryName);

}
