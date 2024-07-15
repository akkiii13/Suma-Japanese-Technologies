package com.sjt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {

	// find list of bookings by customer
	List<Booking> findByCustomerId(Long customerId);

	// find list of bookings by mobile number
	List<Booking> findByMobileNumber(String mobileNumber);

	// find list of bookings by modified date
	List<Booking> findByModifiedDate(LocalDate modifiedDate);

	// find list of bookings by modified date between
	List<Booking> findByModifiedDateBetween(LocalDate startDate, LocalDate endDate);

	// find list of bookings by modified date before
	List<Booking> findByModifiedDateBefore(LocalDate modifiedDate);

	// find list of bookings by modified date after
	List<Booking> findByModifiedDateAfter(LocalDate modifiedDate);

	// find bookings by customer id and booking id
	Booking findByCustomerIdAndBookingId(Long customerId, Long contactUsId);

}
