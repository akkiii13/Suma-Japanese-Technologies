package sjtxev.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sjtxev.products.entities.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {

	// find list of products by numeric value
	List<Product> findByProductIdOrPrice(Long productId, double price);

	// find list of products by string value
	List<Product> findByBrandNameContainingOrModelNameContainingOrDescriptionContainingAllIgnoringCase(String brandName,
			String modelName, String description);

	// find list of products by boolean value
	List<Product> findByAvailability(Boolean availability);

	// find list of products by category
	List<Product> findByCategoryId(Long categoryId);

}
