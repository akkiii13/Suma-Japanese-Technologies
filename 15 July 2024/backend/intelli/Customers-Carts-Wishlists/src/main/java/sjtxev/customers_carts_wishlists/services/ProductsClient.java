package sjtxev.customers_carts_wishlists.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sjtxev.customers_carts_wishlists.entities.Product;

@FeignClient(name = "PRODUCTS-SERVICE", path = "/products")
public interface ProductsClient {

	@GetMapping("/getSingleProductByProductId/{productId}")
	public ResponseEntity<Product> getSingleProductByProductId(@PathVariable("productId") Long productId);

}
