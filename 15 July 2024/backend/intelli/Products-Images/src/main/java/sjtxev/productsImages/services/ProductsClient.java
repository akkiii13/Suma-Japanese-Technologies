package sjtxev.productsImages.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTS-SERVICE", path = "/products")
public interface ProductsClient {

	@GetMapping("/get/checkWhetherTheProductExistsOrNotByProductId/{productId}")
	public Boolean existsSingleProductByProductIdOrNot(@PathVariable("productId") Long productId);

}
