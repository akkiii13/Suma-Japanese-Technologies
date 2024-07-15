package sjtxev.shipments.services;

import java.util.List;

import sjtxev.shipments.payloads.ShipmentDto;

import jakarta.validation.Valid;

public interface ShipmentService {

	// add shipment
	ShipmentDto addShipment(@Valid ShipmentDto shipmentDto, Long customerId);

	// update shipment by shipment id
	ShipmentDto updateShipmentByShipmentId(@Valid ShipmentDto shipmentDto, Long shipmentId);

	// delete single shipment by shipment id
	void deleteSingleShipmentByShipmentId(Long shipmentId);

	// delete list of shipments by customer id
	void deleteListOfShipmentsByCustomerId(Long customerId);

	// get all shipments
	List<ShipmentDto> getAllShipments();

	// get list of shipments by customer id
	List<ShipmentDto> getListOfShipmentsByCustomerId(Long customerId);

	// get single shipment by shipment id
	ShipmentDto getSingleShipmentByShipmentId(Long shipmentId);

	// exists single shipment by shipment id and customer id
	Boolean existsSingleShipmentByShipmentIdAndCustomerId(Long shipmentId, Long customerId);

}
