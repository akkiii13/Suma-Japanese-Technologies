package sjtxev.shipments.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sjtxev.shipments.entities.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	// find list of shipments by customer
	List<Shipment> findByCustomerId(Long customerId);

	// exists single shipment by shipment id and customer id
	Boolean existsByShipmentIdAndCustomerId(Long shipmentId, Long customerId);

}
