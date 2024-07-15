package sjtxev.products.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTS-IMAGES-SERVICE", path = "/productsImages")
public interface ProductsImagesClient {

	@DeleteMapping("/delete/deleteListOfProductImagesByProductId/{productId}")
	public void delteProductImagesByProductId(@PathVariable("productId") Long productId);

	@DeleteMapping("/delete/deleteTheListOfProductImagesByGivenProductIdList/{productIds}")
	public void deleteTheListOfProductImagesByGivenProductIdList(@PathVariable List<Long> productIds);

	@DeleteMapping("/delete/deleteAll")
	public void deleteAllProductsImages();

}
