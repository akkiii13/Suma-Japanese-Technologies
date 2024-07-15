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

import com.sjtev.payloads.InquiryDto;
import com.sjtev.responseObjects.ApiResponse;
import com.sjtev.services.InquiryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inquiries")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	@PostMapping("/save")
	public ResponseEntity<InquiryDto> saveInquiry(@Valid @RequestBody InquiryDto inquiryDto) {
		return new ResponseEntity<InquiryDto>(this.inquiryService.saveInquiry(inquiryDto), HttpStatus.CREATED);
	}

	@PutMapping("/update/updateMobileNumberFieldByInquiryId/{inquiryId}")
	public ResponseEntity<InquiryDto> updateMobileNumberFieldByInquiryId(@Valid String mobileNumber,
			@PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<InquiryDto>(
				this.inquiryService.updateMobileNumberFieldByInquiryId(inquiryId, mobileNumber), HttpStatus.OK);
	}

	@PutMapping("/update/updateRemainingInquiryFieldsByInquiryId/{inquiryId}")
	public ResponseEntity<InquiryDto> updateRemainingInquiryFieldsByInquiryId(@Valid @RequestBody InquiryDto inquiryDto,
			@PathVariable("inquiryId") Long inquiryId) {
		return new ResponseEntity<InquiryDto>(
				this.inquiryService.updateRemainingInquiryFieldsByInquiryId(inquiryDto, inquiryId), HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteSingleInquiryByInquiryId/{inquiryId}")
	public ResponseEntity<ApiResponse> deleteSingleInquiryByInquiryId(@PathVariable("inquiryId") Long inquiryId) {
		this.inquiryService.deleteSingleInquiryByInquiryId(inquiryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Inquiry deleted successfully.", true), HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteListOfSeletedInquiriesByInquiryId/")
	public ResponseEntity<ApiResponse> deleteListOfSeletedInquiriesByInquiryId(List<Long> inquiryIds) {
		this.inquiryService.deleteListOfSeletedInquiriesByInquiryId(inquiryIds);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Inquiries deleted successfully.", true), HttpStatus.OK);
	}

	@DeleteMapping("/delete/deleteAllInquiries")
	public ResponseEntity<ApiResponse> deleteAllInquiries() {
		this.inquiryService.deleteAllInquiries();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All inquiries deleted successfully", true),
				HttpStatus.OK);
	}

	@GetMapping("/get/getSingleInquiryByInquiryInquiryId/{inquiryId}")
	public ResponseEntity<InquiryDto> getSingleInquiryByInquiryInquiryId(@PathVariable("inquirId") Long inquiryId) {
		return new ResponseEntity<InquiryDto>(this.inquiryService.getSingleInquiryByInquiryInquiryId(inquiryId),
				HttpStatus.OK);
	}

	@GetMapping("/get/getListOfInquiriesByDateBefore/{date}")
	public ResponseEntity<List<InquiryDto>> getListOfInquiriesByDateBefore(@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<InquiryDto>>(this.inquiryService.getListOfInquiriesByDateBefore(date),
				HttpStatus.OK);
	}

	@GetMapping("/get/getListOfInquiriesBySpecificDate/{date}")
	public ResponseEntity<List<InquiryDto>> getListOfInquiriesBySpecificDate(@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<InquiryDto>>(this.inquiryService.getListOfInquiriesBySpecificDate(date),
				HttpStatus.OK);
	}

	@GetMapping("/get/getListOfInquiriesByRangeOfDates/startDate/{startDate}/endDate/{endDate}")
	public ResponseEntity<List<InquiryDto>> getListOfInquiriesByRangeOfDates(
			@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
		return new ResponseEntity<List<InquiryDto>>(
				this.inquiryService.getListOfInquiriesByRangeOfDates(startDate, endDate), HttpStatus.OK);
	}

	@GetMapping("/get/getListOfInquiriesByDateAfter/{date}")
	public ResponseEntity<List<InquiryDto>> getListOfInquiriesByDateAfter(@PathVariable("date") LocalDate date) {
		return new ResponseEntity<List<InquiryDto>>(this.inquiryService.getListOfInquiriesByDateAfter(date),
				HttpStatus.OK);
	}

	@GetMapping("/get/getAllInquiries")
	public ResponseEntity<List<InquiryDto>> getAllInquiries() {
		return new ResponseEntity<List<InquiryDto>>(this.inquiryService.getAllInquiries(), HttpStatus.OK);
	}

	@GetMapping("/search/searchByAnyValue/{searchString}")
	public ResponseEntity<List<InquiryDto>> searchByAnyValue(@PathVariable("searchString") String searchString) {
		return new ResponseEntity<List<InquiryDto>>(this.inquiryService.searchByAnyValue(searchString), HttpStatus.OK);
	}

}
