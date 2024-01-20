package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.Customers;
import com.sjt.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	// find list of payments by customer
	List<Payment> findByCustomers(Customers customer);

}
