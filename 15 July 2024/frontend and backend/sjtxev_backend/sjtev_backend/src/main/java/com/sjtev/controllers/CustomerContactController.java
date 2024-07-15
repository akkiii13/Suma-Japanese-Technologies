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

import com.sjtev.payloads.CustomerContactDto;
import com.sjtev.responseObjects.ApiResponse;
import com.sjtev.services.CustomerContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customerContacts")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerContactController {

	@Autowired
	private CustomerContactService customerContactService;

	@PostMapping("/save")
	public ResponseEntity<CustomerContactDto> saveCustomerContact(
			@Valid @RequestBody CustomerContactDto customerContactDto, @PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<CustomerContactDto>(
				this.customerContactService.saveCustomerContact(customerContactDto, inquiryId), HttpStatus.CREATED);
	}

	@PutMapping("/update/customerContactId/{customerContactId}/inquiryId/{inquiryId}")
	public ResponseEntity<CustomerContactDto> updateCustomerContact(
			@Valid @RequestBody CustomerContactDto customerContactDto,
			@PathVariable("customerContactId") Long customerContactId, @PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<CustomerContactDto>(
				this.customerContactService.updateCustomerContact(customerContactDto, customerContactId, inquiryId),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteSingleCustomerContactByCustomerContactIdAndInquiryId/customerContactId/{customerContactId}/inquiryId/{inquiryId}")
	public ResponseEntity<ApiResponse> deleteSingleCustomerContactByCustomerContactIdAndInquiryId(
			@PathVariable("customerContactId") Long customerContactId, @PathVariable("inquiryId") Long inquiryId) {
		this.customerContactService.deleteSingleCustomerContactByCustomerContactIdAndInquiryId(customerContactId,
				inquiryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer contact deleted successfully", true),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteListOfCustomerContactByInquiryId/{inquiryId}")
	public ResponseEntity<ApiResponse> deleteListOfCustomerContactByInquiryId(
			@PathVariable("inquiryId") Long inquiryId) {
		this.customerContactService.deleteListOfCustomerContactByInquiryId(inquiryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer contacts deleted successfully", true),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteAllCustomerContacts")
	public ResponseEntity<ApiResponse> deleteAllCustomerContacts() {
		this.customerContactService.deleteAllCustomerContacts();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All customer contacts deleted successfully", true),
				HttpStatus.OK);
	}

	@GetMapping("/get/getSingleCustomerContactByCustomerContactIdAndInquiryId/customerContactId/{customerContactId}/inquiryId/{inquiryId}")
	public ResponseEntity<CustomerContactDto> getSingleCustomerContactByCustomerContactIdAndInquiryId(
			@PathVariable("customerContactId") Long customerContactId, @PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<CustomerContactDto>(this.customerContactService
				.getSingleCustomerContactByCustomerContactIdAndInquiryId(customerContactId, inquiryId), HttpStatus.OK);
	}

	@GetMapping("/get/getAll")
	public ResponseEntity<List<CustomerContactDto>> getAllCustomerContacts() {
		return new ResponseEntity<List<CustomerContactDto>>(this.customerContactService.getAllCustomerContacts(),
				HttpStatus.OK);
	}

	@GetMapping("/search/searchByCategory/{category}")
	public ResponseEntity<List<CustomerContactDto>> searchByCategory(@PathVariable("category") String category) {
		return new ResponseEntity<List<CustomerContactDto>>(this.customerContactService.searchByCategory(category),
				HttpStatus.OK);
	}

	@GetMapping("/search/searchByNeedToEscalate/{needToEscalate}")
	public ResponseEntity<List<CustomerContactDto>> searchByNeedToEscalate(
			@PathVariable("needToEscalate") Boolean needToEscalate) {
		return new ResponseEntity<List<CustomerContactDto>>(
				this.customerContactService.searchByNeedToEscalate(needToEscalate), HttpStatus.OK);
	}

	@GetMapping("/search/getListOfInquiriesByDateBefore/{date}")
	public ResponseEntity<List<CustomerContactDto>> getListOfInquiriesByDateBefore(
			@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<CustomerContactDto>>(
				this.customerContactService.getListOfInquiriesByDateBefore(date), HttpStatus.OK);
	}

	@GetMapping("/search/getListOfInquiriesBySpecificDate/{specificDate}")
	public ResponseEntity<List<CustomerContactDto>> getListOfInquiriesBySpecificDate(
			@PathVariable("specificDate") LocalDate specificDate) {
		return new ResponseEntity<List<CustomerContactDto>>(
				this.customerContactService.getListOfInquiriesBySpecificDate(specificDate), HttpStatus.OK);
	}

	@GetMapping("/search/getListOfInquiriesByRangeOfDates/startDate/{startDate}/endDate/{endDate}")
	public ResponseEntity<List<CustomerContactDto>> getListOfInquiriesByRangeOfDates(
			@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
		return new ResponseEntity<List<CustomerContactDto>>(
				this.customerContactService.getListOfInquiriesByRangeOfDates(startDate, endDate), HttpStatus.OK);
	}

	@GetMapping("/search/getListOfInquiriesByDateAfter/{date}")
	public ResponseEntity<List<CustomerContactDto>> getListOfInquiriesByDateAfter(
			@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<CustomerContactDto>>(
				this.customerContactService.getListOfInquiriesByDateAfter(date), HttpStatus.OK);
	}

}
