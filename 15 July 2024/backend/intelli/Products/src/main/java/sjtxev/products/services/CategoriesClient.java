package sjtxev.products.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATEGORIES-SERVICE", path = "/categories")
public interface CategoriesClient {

	@GetMapping("/get/existsByCategoryId/{categoryId}")
	public Boolean existsByCategoryId(@PathVariable("categoryId") Long categoryId);

	@GetMapping("/get/getListOfCategoryIdsByCategoryName/{categoryName}")
	public List<Long> findByCategoryName(@PathVariable("categoryName") String categoryName);

}
