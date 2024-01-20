package sjtxev.customers_carts_wishlists.services;

import java.util.List;

import jakarta.validation.Valid;
import sjtxev.customers_carts_wishlists.payloads.CustomerDto;

public interface CustomerService {

	// save new customer
	CustomerDto createNewCustomer(@Valid CustomerDto customerDto);

	// update customer by customer id
	CustomerDto updateCustomerByCustomerId(@Valid CustomerDto customerDto, Long customerId);

	// delete single customer by customer id
	void deleteSingleCustomerByCustomerId(Long customerId);

	// get single customer by customer id
	CustomerDto getSingleCustomerByCustomerId(Long customerId);

	// get list of customers by any value
	List<CustomerDto> getListOfCustomersByAnyValue(Long customerId, String firstName, String lastName, String email,
			String mobileNumber);

	// get all customers
	List<CustomerDto> getAllCustomers();

	// get list of active customers
	List<CustomerDto> getAllActiveCustomers();

	// get list of inactive customers
	List<CustomerDto> getAllInactiveCustomers();

	// get single customer by mobile number
	CustomerDto getByMobileNumber(String mobileNumber);

	// exists single customer by customer id and is active true
	Boolean existsByCustomerIdAndIsActiveTrue(Long customerId);

	// exists single customer by mobile number and is active true
	Boolean existsByMobileNumberAndIsActiveTrue(String mobileNumber);

}
