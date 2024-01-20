package com.sjt.controllers;

import java.time.LocalDate;
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

import com.sjt.payloads.ContactUsDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.ContactUsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contactus")
@Validated
public class ContactUsController {

	@Autowired
	ContactUsService contactUsService;

	// save contact us inquiry
	@PostMapping("/save")
	public ResponseEntity<ContactUsDto> saveContactUsInquiry(@Valid @RequestBody ContactUsDto contactUsDto) {
		return new ResponseEntity<ContactUsDto>(this.contactUsService.saveContactUsInquiry(contactUsDto),
				HttpStatus.CREATED);
	}

	// update contact us inquiry by contact us id
	@PutMapping("/update/{contactUsId}")
	public ResponseEntity<ContactUsDto> updateContactUsInquiryByContactUsId(
			@Valid @RequestBody ContactUsDto contactUsDto, @PathVariable("contactUsId") Long cId) {
		return ResponseEntity.ok(this.contactUsService.updateContactUsInquiryByContactUsId(contactUsDto, cId));
	}

	// delete single contact us inquiry by contact us id
	@DeleteMapping("/delete/deleteSingleContactUsInquiryByContactUsId/{contactUsId}")
	public ResponseEntity<ApiResponse> deleteSingleContactUsInquiryByContactUsId(
			@PathVariable("contactUsId") Long contactUsId) {
		this.contactUsService.deleteSingleContactUsInquiryByContactUsId(contactUsId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Contact us inquiry deleted successfully", true),
				HttpStatus.OK);
	}

	// delete list of contact us inquiry by customer id
	@DeleteMapping("/delete/deleteListOfContactUsInquiriesByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteListOfContactUsInquiriesByCustomerId(
			@PathVariable("customerId") Long customerId) {
		this.contactUsService.deleteListOfContactUsInquiriesByCustomerId(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Contact us inquiry deleted successfully", true),
				HttpStatus.OK);
	}

	// delete all contact us inquiries
	@DeleteMapping("/delete/deleteAllContactUsInquiries")
	public ResponseEntity<ApiResponse> deleteAllContactUsInquiries() {
		this.contactUsService.deleteAllContactUsInquiries();
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("All Contact Us inquries are deleted successfully", true), HttpStatus.OK);
	}

	// get single contact us inquiry by contact us id
	@GetMapping("/getSingleContactUsInquiryByContactUsId/{contactUsId}")
	public ResponseEntity<ContactUsDto> getSingleContactUsInquiryByContactUsId(
			@PathVariable("contactUsId") Long contactUsId) {
		return ResponseEntity.ok(this.contactUsService.getSingleContactUsInquiryByContactUsId(contactUsId));
	}

	// get all contact us inquiries
	@GetMapping("/getAllContactUsInquiries")
	public ResponseEntity<List<ContactUsDto>> getAllContactUsInquiries() {
		return ResponseEntity.ok(this.contactUsService.getAllContactUsInquiries());
	}

	// get list of contact us inquiries by customer id
	@GetMapping("/getListOfContactUsInquiriesByCustomerId/{customerId}")
	public ResponseEntity<List<ContactUsDto>> getListOfContactUsInquiriesByCustomerId(
			@PathVariable("customerId") Long customerId) {
		List<ContactUsDto> allContactUsInquiriesByCustomerId = this.contactUsService
				.getListOfContactUsInquiriesByCustomerId(customerId);
		return new ResponseEntity<List<ContactUsDto>>(allContactUsInquiriesByCustomerId, HttpStatus.OK);
	}

	// get list of contact us inquiries by mobile number
	@GetMapping("/getListOfContactUsInquiriesByMobileNumber/{mobileNumber}")
	public ResponseEntity<List<ContactUsDto>> getListOfContactUsInquiriesByMobileNumber(
			@PathVariable("mobileNumber") String mobileNumber) {
		List<ContactUsDto> allContactUsInquiriesByMobileNumber = this.contactUsService
				.getListOfContactUsInquiriesByMobileNumber(mobileNumber);
		return new ResponseEntity<List<ContactUsDto>>(allContactUsInquiriesByMobileNumber, HttpStatus.OK);
	}

	// get list of contact us inquiries by date
	@GetMapping("/getListOfContactUsInquiriesByDate/{modifiedDate}")
	public ResponseEntity<List<ContactUsDto>> getListOfContactUsInquiriesByDate(
			@PathVariable("modifiedDate") LocalDate modifiedDate) {
		List<ContactUsDto> allContactUsInquiriesByDate = this.contactUsService
				.getListOfContactUsInquiriesByDate(modifiedDate);
		return new ResponseEntity<List<ContactUsDto>>(allContactUsInquiriesByDate, HttpStatus.OK);
	}

	// get list of contact us inquiries by range of date
	@GetMapping("/getListOfContactUsInquiriesByRangeOfDate/startDate/{startDate}/endDate/{endDate}")
	public ResponseEntity<List<ContactUsDto>> getListOfContactUsInquiriesByRangeOfDate(
			@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
		List<ContactUsDto> allContactUsInquiriesByDate = this.contactUsService
				.getListOfContactUsInquiriesByRangeOfDate(startDate, endDate);
		return new ResponseEntity<List<ContactUsDto>>(allContactUsInquiriesByDate, HttpStatus.OK);
	}

	// get list of contact us inquiries by date before
	@GetMapping("/getListOfContactUsInquiriesBeforeDate/{modifiedDate}")
	public ResponseEntity<List<ContactUsDto>> getListOfContactUsInquiriesBeforeDate(
			@PathVariable("modifiedDate") LocalDate modifiedDate) {
		List<ContactUsDto> allContactUsInquiriesByDate = this.contactUsService
				.getListOfContactUsInquiriesBeforeDate(modifiedDate);
		return new ResponseEntity<List<ContactUsDto>>(allContactUsInquiriesByDate, HttpStatus.OK);
	}

	// get list of contact us inquiries by date after
	@GetMapping("/getListOfContactUsInquiriesAfterDate/{modifiedDate}")
	public ResponseEntity<List<ContactUsDto>> getListOfContactUsInquiriesAfterDate(
			@PathVariable("modifiedDate") LocalDate modifiedDate) {
		List<ContactUsDto> allContactUsInquiriesByDate = this.contactUsService
				.getListOfContactUsInquiriesAfterDate(modifiedDate);
		return new ResponseEntity<List<ContactUsDto>>(allContactUsInquiriesByDate, HttpStatus.OK);
	}

}
