package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.Customers;
import com.sjt.entities.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	// find list of shipments by customer
	List<Shipment> findByCustomers(Customers customer);

}
