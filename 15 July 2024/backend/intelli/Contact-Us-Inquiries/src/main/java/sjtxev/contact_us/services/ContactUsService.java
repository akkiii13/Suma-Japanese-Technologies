package sjtxev.contact_us.services;

import java.time.LocalDate;
import java.util.List;

import sjtxev.contact_us.payloads.ContactUsDto;

import jakarta.validation.Valid;

public interface ContactUsService {

	// save contact us inquiry
	ContactUsDto saveContactUsInquiry(@Valid ContactUsDto contactUsDto);

	// update contact us inquiry by contact us id
	ContactUsDto updateContactUsInquiryByContactUsId(@Valid ContactUsDto contactUsDto, @Valid Long contactusId);

	// delete single contact us inquiry by contact us id
	void deleteSingleContactUsInquiryByContactUsId(Long contactUsId);

	// delete list of contact us inquiry by customer id
	void deleteListOfContactUsInquiriesByCustomerId(Long customerId);

	// delete all contact us inquiries
	void deleteAllContactUsInquiries();

	// get single contact us inquiry by contact us id
	ContactUsDto getSingleContactUsInquiryByContactUsId(Long contactUsId);

	// get all contact us inquiries
	List<ContactUsDto> getAllContactUsInquiries();

	// get list of contact us inquiries by customer id
	List<ContactUsDto> getListOfContactUsInquiriesByCustomerId(Long customerId);

	// get single contact us inquiry by customer id and contact us id
	ContactUsDto getSingleContactUsInquiryByCustomerIdAndContactUsId(Long customerId, Long contactUsId);

	// get list of contact us inquiries by mobile number
	List<ContactUsDto> getListOfContactUsInquiriesByMobileNumber(String mobileNumber);

	// get list of contact us inquiries by date
	List<ContactUsDto> getListOfContactUsInquiriesByDate(LocalDate date);

	// get list of contact us inquiries by range of date
	List<ContactUsDto> getListOfContactUsInquiriesByRangeOfDate(LocalDate startDate, LocalDate endDate);

	// get list of contact us inquiries by date before
	List<ContactUsDto> getListOfContactUsInquiriesBeforeDate(LocalDate date);

	// get list of contact us inquiries by date after
	List<ContactUsDto> getListOfContactUsInquiriesAfterDate(LocalDate date);

}
