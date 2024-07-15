package sjtxev.customers_carts_wishlists.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sjtxev.customers_carts_wishlists.payloads.CustomerDto;
import sjtxev.customers_carts_wishlists.responseObjects.ApiResponse;
import sjtxev.customers_carts_wishlists.services.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// save new customer
	@PostMapping("/save")
	public ResponseEntity<CustomerDto> createNewCustomer(@Valid @RequestBody CustomerDto customerDto) {
		return new ResponseEntity<>(this.customerService.createNewCustomer(customerDto), HttpStatus.CREATED);
	}

	// update customer by customer id
	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomerByCustomerId(@Valid @RequestBody CustomerDto customerDto,
			@PathVariable("customerId") Long cId) {
		return ResponseEntity.ok(this.customerService.updateCustomerByCustomerId(customerDto, cId));
	}

	// delete single customer by customer id
	@DeleteMapping("/delete/deleteSingleCustomerByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteSingleCustomerByCustomerId(@PathVariable("customerId") Long cId) {
		this.customerService.deleteSingleCustomerByCustomerId(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted successfully", true), HttpStatus.OK);
	}

	// get single customer by customer id
	@GetMapping("/getSingleCustomerByCustomerId/{customerId}")
	public ResponseEntity<CustomerDto> getSingleCustomerByCustomerId(@PathVariable("customerId") Long cId) {
		return ResponseEntity.ok(this.customerService.getSingleCustomerByCustomerId(cId));
	}

	// get list of customers by any value
	@GetMapping("/get/getListOfCustomersByAnyValue/{searchString}")
	public ResponseEntity<List<CustomerDto>> getListOfCustomersByAnyValue(
			@PathVariable("searchString") String searchString) {
		List<CustomerDto> findByAnyValue1 = new ArrayList<>();
		List<CustomerDto> findByAnyValue2 = new ArrayList<>();
		if (searchString.matches("[0-9]+")) {
			findByAnyValue1 = this.customerService.getListOfCustomersByAnyValue(Long.parseLong(searchString),
					searchString, searchString, searchString, searchString);
		} else
			findByAnyValue2 = this.customerService.getListOfCustomersByAnyValue(0L, searchString, searchString,
					searchString, searchString);
		List<CustomerDto> newList = Stream.concat(findByAnyValue1.stream(), findByAnyValue2.stream()).toList();
		return ResponseEntity.ok(newList);
	}

	// get all customers
	@GetMapping("/get/getAllCustomers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		return ResponseEntity.ok(this.customerService.getAllCustomers());
	}

	// get list of active customers
	@GetMapping("/get/getAllActiveCustomers")
	public ResponseEntity<List<CustomerDto>> getAllActiveCustomers() {
		return ResponseEntity.ok(this.customerService.getAllActiveCustomers());
	}

	// get list of inactive customers
	@GetMapping("/get/getAllInactiveCustomers")
	public ResponseEntity<List<CustomerDto>> getAllInactiveCustomers() {
		return ResponseEntity.ok(this.customerService.getAllInactiveCustomers());
	}

	// get single customer by mobile number
	@GetMapping("/get/getByMobileNumber/{mobileNumber}")
	public ResponseEntity<CustomerDto> getByMobileNumberAndIsActiveTrue(
			@PathVariable("mobileNumber") String mobileNumber) {
		return new ResponseEntity<CustomerDto>(this.customerService.getByMobileNumber(mobileNumber), HttpStatus.OK);
	}

	// exists single customer by customer id and is active true
	@GetMapping("/find/existsByCustomerIdAndIsActiveTrue/{customerId}")
	public Boolean existsByCustomerIdAndIsActiveTrue(@PathVariable("customerId") Long customerId) {
		return this.customerService.existsByCustomerIdAndIsActiveTrue(customerId);
	}

	// exists single customer by mobile number and is active true
	@GetMapping("/find/existsByMobileNumberAndIsActiveTrue/{mobileNumber}")
	public Boolean existsByMobileNumberAndIsActiveTrue(@PathVariable("mobileNumber") String mobileNumber) {
		return this.customerService.existsByMobileNumberAndIsActiveTrue(mobileNumber);
	}

}
