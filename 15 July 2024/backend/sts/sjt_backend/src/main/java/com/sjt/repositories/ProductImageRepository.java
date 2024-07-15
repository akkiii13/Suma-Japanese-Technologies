package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sjt.entities.ProductImage;
import com.sjt.entities.Products;
import com.sjt.entities.entityHelper.ProductImagePrimaryKey;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImagePrimaryKey> {

	// find list of productImage by products
	List<ProductImage> findByProducts(Products products);

	// find single productImage by productImage id and product id
	ProductImage findByProductImageId(ProductImagePrimaryKey productImagePrimaryKey);

	List<ProductImage> findByNameContaining(String fileName);

	// find max productImage id by product id
	@Query("SELECT MAX(p.productImageId.productImageIdPK) FROM ProductImage p WHERE p.productImageId.productIdPK = :productIdPK")
	Long findMaxProductImageIdByProductId(Long productIdPK);

}
