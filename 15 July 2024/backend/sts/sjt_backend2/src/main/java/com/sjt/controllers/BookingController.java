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

import com.sjt.payloads.BookingDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
@Validated
public class BookingController {

	@Autowired
	BookingService bookingService;

	// save booking
	@PostMapping("/save")
	public ResponseEntity<BookingDto> saveContactUsInquiry(@Valid @RequestBody BookingDto bookingDto) {
		return new ResponseEntity<BookingDto>(this.bookingService.saveBooking(bookingDto), HttpStatus.CREATED);
	}

	// update booking by booking id
	@PutMapping("/update/{bookingId}")
	public ResponseEntity<BookingDto> updateContactUsInquiryByContactUsId(@Valid @RequestBody BookingDto bookingDto,
			@PathVariable("bookingId") Long bookingId) {
		return ResponseEntity.ok(this.bookingService.updateBookingByContactUsId(bookingDto, bookingId));
	}

	// delete single booking by booking id
	@DeleteMapping("/delete/deleteSingleBookingByBookingId/{bookingId}")
	public ResponseEntity<ApiResponse> deleteSingleBookingByBookingId(@PathVariable("bookingId") Long bookingId) {
		this.bookingService.deleteSingleBookingByBookingId(bookingId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Booking deleted successfully", true), HttpStatus.OK);
	}

	// delete list of bookings by customer id
	@DeleteMapping("/delete/deleteListOfBookingsByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteListOfBookingsByCustomerId(@PathVariable("customerId") Long customerId) {
		this.bookingService.deleteListOfBookingsByCustomerId(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Booking deleted successfully", true), HttpStatus.OK);
	}

	// delete all bookings
	@DeleteMapping("/delete/deleteAllBookings")
	public ResponseEntity<ApiResponse> deleteAllBookings() {
		this.bookingService.deleteAllBookings();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All bookings are deleted successfully", true),
				HttpStatus.OK);
	}

	// get single booking by booking id
	@GetMapping("/getSingleContactUsInquiryByContactUsId/{bookingId}")
	public ResponseEntity<BookingDto> getSingleContactUsInquiryByContactUsId(
			@PathVariable("bookingId") Long bookingId) {
		return ResponseEntity.ok(this.bookingService.getSingleBookingByBookingId(bookingId));
	}

	// get all bookings
	@GetMapping("/getAllBookings")
	public ResponseEntity<List<BookingDto>> getAllBookings() {
		return ResponseEntity.ok(this.bookingService.getAllBookings());
	}

	// get list of bookings by customer id
	@GetMapping("/getListOfBookingsByCustomerId/{bookingId}")
	public ResponseEntity<List<BookingDto>> getListOfBookingsByCustomerId(@PathVariable("bookingId") Long bookingId) {
		List<BookingDto> allBookingsByCustomerId = this.bookingService.getListOfBookingsByCustomerId(bookingId);
		return new ResponseEntity<List<BookingDto>>(allBookingsByCustomerId, HttpStatus.OK);
	}

	// get single booking by customer id and booking id
	@GetMapping("/getSingleBookingByCustomerIdAndBookingId/customerId/{customerId}/bookingId/{bookingId}")
	public ResponseEntity<BookingDto> getSingleBookingByCustomerIdAndBookingId(
			@PathVariable("customerId") Long customerId, @PathVariable("bookingId") Long bookingId) {
		BookingDto allBookingsByCustomerId = this.bookingService.getSingleBookingByCustomerIdAndBookingId(customerId,
				bookingId);
		return new ResponseEntity<BookingDto>(allBookingsByCustomerId, HttpStatus.OK);
	}

	// get list of bookings by mobile number
	@GetMapping("/getListOfBookingsByMobileNumber/{mobileNumber}")
	public ResponseEntity<List<BookingDto>> getListOfBookingsByMobileNumber(
			@PathVariable("mobileNumber") String mobileNumber) {
		List<BookingDto> allBookingsByMobileNumber = this.bookingService.getListOfBookingsByMobileNumber(mobileNumber);
		return new ResponseEntity<List<BookingDto>>(allBookingsByMobileNumber, HttpStatus.OK);
	}

	// get list of bookings by date
	@GetMapping("/getListOfBookingsByDate/{modifiedDate}")
	public ResponseEntity<List<BookingDto>> getListOfBookingsByDate(
			@PathVariable("modifiedDate") LocalDate modifiedDate) {
		List<BookingDto> allBookingsByDate = this.bookingService.getListOfBookingsByDate(modifiedDate);
		return new ResponseEntity<List<BookingDto>>(allBookingsByDate, HttpStatus.OK);
	}

	// get list of bookings by range of date
	@GetMapping("/getListOfBookingsByRangeOfDate/startDate/{startDate}/endDate/{endDate}")
	public ResponseEntity<List<BookingDto>> getListOfBookingsByRangeOfDate(
			@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
		List<BookingDto> allBookingsByDate = this.bookingService.getListOfBookingsByRangeOfDate(startDate, endDate);
		return new ResponseEntity<List<BookingDto>>(allBookingsByDate, HttpStatus.OK);
	}

	// get list of bookings by date before
	@GetMapping("/getListOfBookingsBeforeDate/{modifiedDate}")
	public ResponseEntity<List<BookingDto>> getListOfBookingsBeforeDate(
			@PathVariable("modifiedDate") LocalDate modifiedDate) {
		List<BookingDto> allBookingsByDate = this.bookingService.getListOfBookingsBeforeDate(modifiedDate);
		return new ResponseEntity<List<BookingDto>>(allBookingsByDate, HttpStatus.OK);
	}

	// get list of bookings by date after
	@GetMapping("/getListOfBookingsAfterDate/{modifiedDate}")
	public ResponseEntity<List<BookingDto>> getListOfBookingsAfterDate(
			@PathVariable("modifiedDate") LocalDate modifiedDate) {
		List<BookingDto> allBookingsByDate = this.bookingService.getListOfBookingsAfterDate(modifiedDate);
		return new ResponseEntity<List<BookingDto>>(allBookingsByDate, HttpStatus.OK);
	}

}