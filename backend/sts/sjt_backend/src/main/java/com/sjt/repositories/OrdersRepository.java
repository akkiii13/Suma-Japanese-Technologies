package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.Customers;
import com.sjt.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	// find list of orders by customer
	List<Orders> findByCustomers(Customers customers);

}
