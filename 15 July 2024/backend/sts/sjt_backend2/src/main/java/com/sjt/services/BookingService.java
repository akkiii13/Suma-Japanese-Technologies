package com.sjt.services;

import java.time.LocalDate;
import java.util.List;

import com.sjt.payloads.BookingDto;

import jakarta.validation.Valid;

public interface BookingService {

	// save booking
	BookingDto saveBooking(@Valid BookingDto bookingDto);

	// update booking by booking id
	BookingDto updateBookingByContactUsId(@Valid BookingDto bookingDto, @Valid Long bookingId);

	// delete single booking by booking id
	void deleteSingleBookingByBookingId(Long bookingId);

	// delete list of bookings by customer id
	void deleteListOfBookingsByCustomerId(Long customerId);

	// delete all bookings
	void deleteAllBookings();

	// get single booking by booking id
	BookingDto getSingleBookingByBookingId(Long bookingId);

	// get all bookings
	List<BookingDto> getAllBookings();

	// get list of bookings by customer id
	List<BookingDto> getListOfBookingsByCustomerId(Long customerId);

	// get single booking by customer id and booking id
	BookingDto getSingleBookingByCustomerIdAndBookingId(Long customerId, Long bookingId);

	// get list of bookings by mobile number
	List<BookingDto> getListOfBookingsByMobileNumber(String mobileNumber);

	// get list of bookings by date
	List<BookingDto> getListOfBookingsByDate(LocalDate date);

	// get list of bookings by range of date
	List<BookingDto> getListOfBookingsByRangeOfDate(LocalDate startDate, LocalDate endDate);

	// get list of bookings by date before
	List<BookingDto> getListOfBookingsBeforeDate(LocalDate date);

	// get list of bookings by date after
	List<BookingDto> getListOfBookingsAfterDate(LocalDate date);

}
