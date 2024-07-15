package com.sjt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjt.payloads.ShipmentDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.ShipmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shipment")
@Validated
public class ShipmentController {

	@Autowired
	ShipmentService shipmentService;

	// add shipment
	@PostMapping("/save/{customerId}")
	public ResponseEntity<ShipmentDto> addShipment(@Valid @RequestBody ShipmentDto shipmentDto,
			@Valid @PathVariable(name = "customerId") Long customerId) {
		return new ResponseEntity<ShipmentDto>(this.shipmentService.addShipment(shipmentDto, customerId),
				HttpStatus.CREATED);
	}

	// update shipment by shipment id
	@PutMapping("/update/{shipmentId}")
	public ResponseEntity<ShipmentDto> updateShipmentByShipmentId(@Valid @RequestBody ShipmentDto shipmentDto,
			@PathVariable("shipmentId") Long shipmentId) {
		return ResponseEntity.ok(this.shipmentService.updateShipmentByShipmentId(shipmentDto, shipmentId));
	}

	// delete single shipment by shipment id
	@DeleteMapping("/delete/deleteSingleShipmentByShipmentId/{shipmentId}")
	public ResponseEntity<ApiResponse> deleteSingleShipmentByShipmentId(@PathVariable("shipmentId") Long shipmentId) {
		this.shipmentService.deleteSingleShipmentByShipmentId(shipmentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Shipment deleted successfully", true), HttpStatus.OK);
	}

	// delete list of shipments by customer id
	@DeleteMapping("/delete/deleteListOfShipmentsByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteListOfShipmentsByCustomerId(@PathVariable("customerId") Long customerId) {
		this.shipmentService.deleteListOfShipmentsByCustomerId(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("All shipments are deleted successfully", true),
				HttpStatus.OK);
	}

	// get all shipments
	@GetMapping("/getAllShipments")
	public ResponseEntity<List<ShipmentDto>> getAllShipments() {
		return ResponseEntity.ok(this.shipmentService.getAllShipments());
	}

	// get list of shipments by customer id
	@GetMapping("/getListOfShipmentsByCustomerId/{customerId}")
	public ResponseEntity<List<ShipmentDto>> getListOfShipmentsByCustomerId(
			@PathVariable("customerId") Long customerId) {
		List<ShipmentDto> shipmentListByCustomerId = this.shipmentService.getListOfShipmentsByCustomerId(customerId);
		return new ResponseEntity<List<ShipmentDto>>(shipmentListByCustomerId, HttpStatus.OK);
	}

	// get single shipment by shipment id
	@GetMapping("/getSingleShipmentByShipmentId/{shipmentId}")
	public ResponseEntity<ShipmentDto> getSingleShipmentByShipmentId(@PathVariable("shipmentId") Long shipmentId) {
		return ResponseEntity.ok(this.shipmentService.getSingleShipmentByShipmentId(shipmentId));
	}

}
