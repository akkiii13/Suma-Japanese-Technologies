package sjtxev.contact_us.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sjtxev.contact_us.entities.Customer;

@FeignClient(name = "CUSTOMERS-CARTS-WISHLISTS-SERVICE", path = "/customers")
public interface CustomersClient {

	@GetMapping("/get/getByMobileNumber/{mobileNumber}")
	public ResponseEntity<Customer> findByMobileNumber(@PathVariable("mobileNumber") String mobileNumber);

}
