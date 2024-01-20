package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entities.ContactUs;

public interface ContactUsService {

	// get inquiries by date
	List<ContactUs> getContactUsInquiriesByDate(LocalDate date);

	// get inquiries by range of date
	List<ContactUs> getContactUsInquiriesByRangeOfDate(LocalDate startDate, LocalDate endDate);

	// get inquiries by date before
	List<ContactUs> getInquiryListBeforeDate(LocalDate date);

	// get inquiries by date after
	List<ContactUs> getInquiryListAfterDate(LocalDate date);

}
