package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.Category;
import com.sjt.entities.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

	// find list of products by numeric value
	List<Products> findByProductIdOrPrice(Long productId, double price);

	// find list of products by string value
	List<Products> findByBrandNameContainingOrModelNameContainingOrDescriptionContainingAllIgnoringCase(
			String brandName, String modelName, String description);

	// find list of products by boolean value
	List<Products> findByAvailability(Boolean availability);

	// find list of products by category
	List<Products> findByCategory(Category category);

}
