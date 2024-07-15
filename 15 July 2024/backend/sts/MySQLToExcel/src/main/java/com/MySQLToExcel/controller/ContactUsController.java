package com.MySQLToExcel.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MySQLToExcel.entities.ContactUs;
import com.MySQLToExcel.services.ContactUsService;

@Controller
@RequestMapping("/contactus")
public class ContactUsController {

	@Autowired
	ContactUsService contactUsService;

	public ContactUsController(ContactUsService contactUsService) {
		super();
	}

	@GetMapping("/getContactUsInquiriesByDate")
	public ResponseEntity<List<ContactUs>> getContactUsInquiriesByDate() {
		LocalDate date = LocalDate.now().minusDays(1);
		return ResponseEntity.ok(this.contactUsService.getContactUsInquiriesByDate(date));
	}

	@GetMapping("/getContactUsInquiriesByRangeOfDate")
	public ResponseEntity<List<ContactUs>> getContactUsInquiriesByRangeOfDate() {
		LocalDate startDate = LocalDate.now().minusDays(1);
		return ResponseEntity.ok(this.contactUsService.getContactUsInquiriesByRangeOfDate(startDate, LocalDate.now()));
	}

	@GetMapping("/getInquiryListBeforeDate")
	public ResponseEntity<List<ContactUs>> getInquiryListBeforeDate() {
		return ResponseEntity.ok(this.contactUsService.getInquiryListBeforeDate(LocalDate.now()));
	}

	@GetMapping("/getInquiryListAfterDate")
	public ResponseEntity<List<ContactUs>> getInquiryListAfterDate() {
		LocalDate date = LocalDate.now().minusDays(3);
		return ResponseEntity.ok(this.contactUsService.getInquiryListAfterDate(date));
	}

}
