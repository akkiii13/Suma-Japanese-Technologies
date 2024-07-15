package com.sjtev.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtev.payloads.VehicleDto;
import com.sjtev.responseObjects.ApiResponse;
import com.sjtev.services.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicles")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/save/{inquiryId}")
	public ResponseEntity<VehicleDto> saveVehicle(@Valid @RequestBody VehicleDto vehicleDto,
			@PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<VehicleDto>(this.vehicleService.saveVehicle(vehicleDto, inquiryId),
				HttpStatus.CREATED);
	}

	@PutMapping("/update/vehicleId/{vehicleId}/inquiryId/{inquiryId}")
	public ResponseEntity<VehicleDto> updateVehicleByVehicleIdAndInquiryId(@Valid @RequestBody VehicleDto vehicleDto,
			@PathVariable("vehicleId") Long vehicleId, @PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<VehicleDto>(
				this.vehicleService.updateVehicleByVehicleIdAndInquiryId(vehicleDto, vehicleId, inquiryId),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteSingleVehicleByVehicleIdAndInquiryId/{vehicleId}/inquiryId/{inquiryId}")
	public ResponseEntity<ApiResponse> deleteSingleVehicleByVehicleIdAndInquiryId(
			@PathVariable("vehicleId") Long vehicleId, @PathVariable("inquiryId") Long inquiryId) {
		this.vehicleService.deleteSingleVehicleByVehicleIdAndInquiryId(vehicleId, inquiryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Vehicle deleteded successfully", true), HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteListOfVehiclesByInquiryId/{inquiryid}")
	public ResponseEntity<ApiResponse> deleteListOfVehiclesByInquiryId(@PathVariable("inquiryId") Long inquiryId) {
		this.vehicleService.deleteListOfVehiclesByInquiryId(inquiryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Vehicles deleteded successfully", true), HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteAllVehicles")
	public ResponseEntity<ApiResponse> deleteAllVehicles() {
		this.vehicleService.deleteAllVehicles();
		this.vehicleService.deleteAllVehicles();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All vehicles deleteded successfully", true),
				HttpStatus.OK);
	}

	@GetMapping("/get/getSingleVehicleByVehicleIdAndInquiryId/{vehicleId}/inquiryId/{inquiryId}")
	public ResponseEntity<VehicleDto> getSingleVehicleByVehicleIdAndInquiryId(@PathVariable("vehicleId") Long vehicleId,
			@PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<VehicleDto>(
				this.vehicleService.getSingleVehicleByVehicleIdAndInquiryId(vehicleId, inquiryId), HttpStatus.OK);
	}

	@GetMapping("/get/getListOfVehiclesByInquiryId/{inquiryId}")
	public ResponseEntity<List<VehicleDto>> getListOfVehiclesByInquiryId(@PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.getListOfVehiclesByInquiryId(inquiryId),
				HttpStatus.OK);
	}

	@GetMapping("/get/getAllVehicles")
	public ResponseEntity<List<VehicleDto>> getAllVehicles() {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.getAllVehicles(), HttpStatus.OK);
	}

	@GetMapping("/search/searchListOfVehiclesByAnyValue/{searchString}")
	public ResponseEntity<List<VehicleDto>> searchListOfVehiclesByAnyValue(
			@PathVariable("searchString") String searchString) {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.searchListOfVehiclesByAnyValue(searchString),
				HttpStatus.OK);
	}

	@GetMapping("/search/searchByCreatedDateBefore/{date}")
	public ResponseEntity<List<VehicleDto>> searchByCreatedDateBefore(@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.searchByCreatedDateBefore(date), HttpStatus.OK);
	}

	@GetMapping("/search/searchByCreatedDate/{specificDate}")
	public ResponseEntity<List<VehicleDto>> searchByCreatedDate(@PathVariable("specificDate") LocalDate specificDate) {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.searchByCreatedDate(specificDate),
				HttpStatus.OK);
	}

	@GetMapping("/search/searchByCreatedDateBetween/startDate/{startDate}/endDate/{endDate}")
	public ResponseEntity<List<VehicleDto>> searchByCreatedDateBetween(@PathVariable("startDate") LocalDate startDate,
			@PathVariable("endDate") LocalDate endDate) {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.searchByCreatedDateBetween(startDate, endDate),
				HttpStatus.OK);
	}

	@GetMapping("/search/searchByCreatedDateAfter/{date}")
	public ResponseEntity<List<VehicleDto>> searchByCreatedDateAfter(@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<VehicleDto>>(this.vehicleService.searchByCreatedDateAfter(date), HttpStatus.OK);
	}

}
