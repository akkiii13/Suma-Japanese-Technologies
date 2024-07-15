package com.sjt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjt.entities.ContactUs;
import com.sjt.entities.Customers;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {

	// find list of contact us inquiries by customer
	List<ContactUs> findByCustomers(Customers customers);

	// find list of contact us inquiries by mobile number
	List<ContactUs> findByMobileNumber(String mobileNumber);

	// find list of contact us inquiries by modified date
	List<ContactUs> findByModifiedDate(LocalDate modifiedDate);

	// find list of contact us inquiries by modified date between
	List<ContactUs> findByModifiedDateBetween(LocalDate startDate, LocalDate endDate);

	// find list of contact us inquiries by modified date before
	List<ContactUs> findByModifiedDateBefore(LocalDate modifiedDate);

	// find list of contact us inquiries by modified date after
	List<ContactUs> findByModifiedDateAfter(LocalDate modifiedDate);

}
