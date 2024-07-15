package sjtxev.orders_orderItems.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMERS-CARTS-WISHLISTS-SERVICE", path = "/customers")
public interface CustomersClient {

	@GetMapping("/find/existsByCustomerIdAndIsActiveTrue/{customerId}")
	public Boolean existsByCustomerIdAndIsActiveTrue(@PathVariable("customerId") Long customerId);

}
