package sjtxev.customers_carts_wishlists.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sjtxev.customers_carts_wishlists.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// to find list of customers by any value
	List<Customer> findByCustomerIdOrFirstNameContainingOrLastNameContainingOrEmailOrMobileNumber(Long customerId,
			String firstName, String lastName, String email, String mobileNumber);

	// authentication of customer
	Customer findByMobileNumberAndPassword(String mobileNumber, String password);

	// find list of customers by mobile number
	Customer findByMobileNumber(String mobileNumber);

	// find list of customers who is active
	List<Customer> findByIsActiveTrue();

	// find list of customers who is inactive
	List<Customer> findByIsActiveFalse();

	// find list of customers by email id
	Customer findByEmail(String mobileNumber);

	// find list of customer by customer id and check whether the customer is active or not
	Optional<Customer> findByCustomerIdAndIsActiveTrue(Long customerId);

	// exists single customer by customer id and is active true
	Boolean existsByCustomerIdAndIsActive(Long customerId, Boolean isActiveStatus);

	// exists single customer by mobile number and is active true
	Boolean existsByMobileNumberAndIsActive(String mobileNumber, Boolean isActiveStatus);

}
