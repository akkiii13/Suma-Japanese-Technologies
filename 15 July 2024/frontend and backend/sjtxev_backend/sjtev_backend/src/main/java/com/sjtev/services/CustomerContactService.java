package com.sjtev.services;

import java.time.LocalDate;
import java.util.List;

import com.sjtev.payloads.CustomerContactDto;

public interface CustomerContactService {

	// save customer contact
	CustomerContactDto saveCustomerContact(CustomerContactDto customerContactDto, Long inquiryId);

	// update customer contact
	CustomerContactDto updateCustomerContact(CustomerContactDto customerContactDto, Long customerContactId,
			Long inquiryId);

	// delete single
	void deleteSingleCustomerContactByCustomerContactIdAndInquiryId(Long customerContactId, Long inquiryId);

	// delete by inquiry id
	void deleteListOfCustomerContactByInquiryId(Long inquiryId);

	// delete all
	void deleteAllCustomerContacts();

	// get single
	CustomerContactDto getSingleCustomerContactByCustomerContactIdAndInquiryId(Long customerContactId, Long inquiryId);

	// get All
	List<CustomerContactDto> getAllCustomerContacts();

	// search by category
	List<CustomerContactDto> searchByCategory(String category);

	// search by need to escalate
	List<CustomerContactDto> searchByNeedToEscalate(Boolean needToEscalate);

	// get list by date before
	List<CustomerContactDto> getListOfInquiriesByDateBefore(LocalDate date);

	// get list by specific date
	List<CustomerContactDto> getListOfInquiriesBySpecificDate(LocalDate specificDate);

	// get list by range of dates
	List<CustomerContactDto> getListOfInquiriesByRangeOfDates(LocalDate startDate, LocalDate endDate);

	// get list by date after
	List<CustomerContactDto> getListOfInquiriesByDateAfter(LocalDate date);
}
