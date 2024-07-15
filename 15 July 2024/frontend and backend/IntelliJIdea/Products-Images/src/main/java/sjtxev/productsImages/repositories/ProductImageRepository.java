package sjtxev.productsImages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import sjtxev.productsImages.entities.ProductImage;
import sjtxev.productsImages.entities.entityHelper.ProductImagePrimaryKey;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImagePrimaryKey> {

	// find list of productImage by products
	List<ProductImage> findByProductId(Long productId);

	// find single productImage by productImage id and product id
	ProductImage findByProductImageId(ProductImagePrimaryKey productImagePrimaryKey);

	List<ProductImage> findByNameContaining(String fileName);

	// find max productImage id by product id
	@Query("SELECT MAX(p.productImageId.productImageIdPK) FROM ProductImage p WHERE p.productImageId.productIdPK = :productIdPK")
	Long findMaxProductImageIdByProductId(Long productIdPK);

	void deleteByProductId(Long productId);
}
