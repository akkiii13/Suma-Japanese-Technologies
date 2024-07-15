package com.sjtev.services;

import java.time.LocalDate;
import java.util.List;

import com.sjtev.payloads.InquiryDto;

public interface InquiryService {

	// save inquiry
	InquiryDto saveInquiry(InquiryDto inquiryDto);

	// update mobile number field by inquiry id
	InquiryDto updateMobileNumberFieldByInquiryId(Long inquiryId, String mobileNumber);

	// update remaining inquiry fields by inquiry id
	InquiryDto updateRemainingInquiryFieldsByInquiryId(InquiryDto inquiryDto, Long inquiryId);

	// delete single inquiry by inquiry id
	void deleteSingleInquiryByInquiryId(Long inquiryId);

	// delete list of inquiries by inquiry id
	void deleteListOfSeletedInquiriesByInquiryId(List<Long> inquiryIds);

	// delete all inquiries
	void deleteAllInquiries();

	// get single inquiry by inquiry inquiry id
	InquiryDto getSingleInquiryByInquiryInquiryId(Long inquiryId);

	// get list of inquiries by date before
	List<InquiryDto> getListOfInquiriesByDateBefore(LocalDate date);

	// get list of inquiries by specific date
	List<InquiryDto> getListOfInquiriesBySpecificDate(LocalDate specificDate);

	// get list of inquiries by range of dates
	List<InquiryDto> getListOfInquiriesByRangeOfDates(LocalDate startDate, LocalDate endDate);

	// get list of inquiries by date after
	List<InquiryDto> getListOfInquiriesByDateAfter(LocalDate date);

	// get all inquiries
	List<InquiryDto> getAllInquiries();

	// search inquiries by full name or mobile number or email or brand name or
	// model name or model year value
	List<InquiryDto> searchByAnyValue(String searchString);

}
