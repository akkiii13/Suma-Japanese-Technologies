package sjtxev.categories.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTS-SERVICE", path = "/products")
public interface ProductsClient {

	@DeleteMapping("/delete/deleteListOfProductsByCategoryId/{categoryId}")
	public void deleteListOfProductsByCategoryId(@PathVariable("categoryId") Long categoryId);

}
