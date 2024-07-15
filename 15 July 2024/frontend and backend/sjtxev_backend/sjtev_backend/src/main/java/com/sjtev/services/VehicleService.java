package com.sjtev.services;

import java.time.LocalDate;
import java.util.List;

import com.sjtev.payloads.VehicleDto;

public interface VehicleService {

	// save vehicle
	VehicleDto saveVehicle(VehicleDto vehicleDto, Long inquiryId);

	// update by vehicle id and inquiry id
	VehicleDto updateVehicleByVehicleIdAndInquiryId(VehicleDto vehicleDto, Long vehicleId, Long inquiryId);

	// delete single
	void deleteSingleVehicleByVehicleIdAndInquiryId(Long vehicleId, Long inquiryId);

	// delete list of vehicles by inquiry id
	void deleteListOfVehiclesByInquiryId(Long inquiryId);

	// delete all
	void deleteAllVehicles();

	// get single vehicle by vehicle id and inquiry id
	VehicleDto getSingleVehicleByVehicleIdAndInquiryId(Long vehicleId, Long inquiryId);

	// get list of vehicles by inquiry id
	List<VehicleDto> getListOfVehiclesByInquiryId(Long inquiryId);

	// get all vehicles
	List<VehicleDto> getAllVehicles();

	// search list of vehicles by any value
	List<VehicleDto> searchListOfVehiclesByAnyValue(String value);

	// search by created date before
	List<VehicleDto> searchByCreatedDateBefore(LocalDate date);

	// search by created date
	List<VehicleDto> searchByCreatedDate(LocalDate date);

	// search by created date between
	List<VehicleDto> searchByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

	// search by created date after
	List<VehicleDto> searchByCreatedDateAfter(LocalDate date);

}
