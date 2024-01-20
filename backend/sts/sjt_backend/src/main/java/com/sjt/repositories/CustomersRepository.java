package com.sjt.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

	// to find list of customers by any value
	List<Customers> findByCustomerIdOrFirstNameContainingOrLastNameContainingOrEmailOrMobileNumber(Long customerId,
			String firstName, String lastName, String email, String mobileNumber);

	// authentication of customer
	Customers findByMobileNumberAndPassword(String mobileNumber, String password);

	// find list of customers by mobile number
	Customers findByMobileNumber(String mobileNumber);

	// find list of customers who is active
	List<Customers> findByIsActiveTrue();

	// find list of customers who is inactive
	List<Customers> findByIsActiveFalse();

	// check whether the customer is exists by mobile number
	boolean existsByMobileNumberAndIsActive(String mobileNumber, boolean status);

	// find list of customers by email id
	Customers findByEmail(String mobileNumber);

	// find list of customer by customer id and check whether the customer is active
	// or not
	Optional<Customers> findByCustomerIdAndIsActiveTrue(Long customerId);

}
