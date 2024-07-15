package com.sjt.controllers;

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

import com.sjt.payloads.CustomersDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.CustomersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@CrossOrigin("http://localhost:3000")
@Validated
public class CustomersController {

	@Autowired
	private CustomersService customersService;

	public CustomersController(CustomersService customersService) {
		super();
	}

	// save new customer
	@PostMapping("/save")
	public ResponseEntity<CustomersDto> createNewCustomer(@Valid @RequestBody CustomersDto customersDto) {
		return new ResponseEntity<>(this.customersService.createNewCustomer(customersDto), HttpStatus.CREATED);
	}

	// update customer by customer id
	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomersDto> updateCustomerByCustomerId(@Valid @RequestBody CustomersDto customersDto,
			@PathVariable("customerId") Long cId) {
		return ResponseEntity.ok(this.customersService.updateCustomerByCustomerId(customersDto, cId));
	}

	// delete single customer by customer id
	@DeleteMapping("/delete/deleteSingleCustomerByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteSingleCustomerByCustomerId(@PathVariable("customerId") Long cId) {
		this.customersService.deleteSingleCustomerByCustomerId(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted successfully", true), HttpStatus.OK);
	}

	// get single customer by customer id
	@GetMapping("/getSingleCustomerByCustomerId/{customerId}")
	public ResponseEntity<CustomersDto> getSingleCustomerByCustomerId(@PathVariable("customerId") Long cId) {
		return ResponseEntity.ok(this.customersService.getSingleCustomerByCustomerId(cId));
	}

	// get list of customers by any value
	@GetMapping("/get/getListOfCustomersByAnyValue/{searchString}")
	public ResponseEntity<List<CustomersDto>> getListOfCustomersByAnyValue(
			@PathVariable("searchString") String searchString) {
		List<CustomersDto> findByAnyValue1 = new ArrayList<>();
		List<CustomersDto> findByAnyValue2 = new ArrayList<>();
		if (searchString.matches("[0-9]+")) {
			findByAnyValue1 = this.customersService.getListOfCustomersByAnyValue(Long.parseLong(searchString),
					searchString, searchString, searchString, searchString);
		} else
			findByAnyValue2 = this.customersService.getListOfCustomersByAnyValue(0L, searchString, searchString,
					searchString, searchString);
		List<CustomersDto> newList = Stream.concat(findByAnyValue1.stream(), findByAnyValue2.stream()).toList();
		return ResponseEntity.ok(newList);
	}

	// get all customers
	@GetMapping("/get/getAllCustomers")
	public ResponseEntity<List<CustomersDto>> getAllCustomers() {
		return ResponseEntity.ok(this.customersService.getAllCustomers());
	}

	// get list of active customers
	@GetMapping("/get/getAllActiveCustomers")
	public ResponseEntity<List<CustomersDto>> getAllActiveCustomers() {
		return ResponseEntity.ok(this.customersService.getAllActiveCustomers());
	}

	// get list of inactive customers
	@GetMapping("/get/getAllInactiveCustomers")
	public ResponseEntity<List<CustomersDto>> getAllInactiveCustomers() {
		return ResponseEntity.ok(this.customersService.getAllInactiveCustomers());
	}

}
