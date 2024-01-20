package sjtxev.orders_orderItems.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import sjtxev.orders_orderItems.entities.Product;

@FeignClient(name = "PRODUCTS-SERVICE", path = "/products")
public interface ProductsClient {

	@GetMapping("/getSingleProductByProductId/{productId}")
	public ResponseEntity<Product> findProductByProductId(@PathVariable("productId") Long productId);

	@PutMapping("/update/{productId}")
	public void updateProductByProductId(Product product, @PathVariable("productId") Long productId);
}
