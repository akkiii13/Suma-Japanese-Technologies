package com.sjt.services;

import java.util.List;

import com.sjt.payloads.CustomersDto;

import jakarta.validation.Valid;

public interface CustomersService {

	// save new customer
	CustomersDto createNewCustomer(@Valid CustomersDto customersDto);

	// update customer by customer id
	CustomersDto updateCustomerByCustomerId(@Valid CustomersDto customersDto, Long customerId);

	// delete single customer by customer id
	void deleteSingleCustomerByCustomerId(Long customerId);

	// get single customer by customer id
	CustomersDto getSingleCustomerByCustomerId(Long customerId);

	// get list of customers by any value
	List<CustomersDto> getListOfCustomersByAnyValue(Long customerId, String firstName, String lastName, String email,
			String mobileNumber);

	// get all customers
	List<CustomersDto> getAllCustomers();

	// get list of active customers
	List<CustomersDto> getAllActiveCustomers();

	// get list of inactive customers
	List<CustomersDto> getAllInactiveCustomers();

}
