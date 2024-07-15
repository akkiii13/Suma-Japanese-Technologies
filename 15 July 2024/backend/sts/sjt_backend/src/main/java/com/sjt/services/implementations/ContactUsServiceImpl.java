package com.sjt.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjt.entities.ContactUs;
import com.sjt.entities.Customers;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.ContactUsDto;
import com.sjt.repositories.ContactUsRepository;
import com.sjt.repositories.CustomersRepository;
import com.sjt.services.ContactUsService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	ContactUsRepository contactUsRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	ModelMapper modelMapper;

	// save contact us inquiry
	@Override
	public ContactUsDto saveContactUsInquiry(@Valid ContactUsDto contactUsDto) {

		String mobileNumber = contactUsDto.getMobileNumber();

		ContactUs contactUs = this.mapToContactUs(contactUsDto);

		contactUs.setModifiedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		contactUs.setModifiedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		Customers customer = null;

		if (mobileNumber != null)
			customer = this.customersRepository.findByMobileNumber(mobileNumber);

		if (customer != null)
			contactUs.setCustomers(customer);

		ContactUs savedContactUs = this.contactUsRepository.save(contactUs);

		return this.mapToContactUsDto(savedContactUs);

	}

	// update contact us inquiry by contact us id
	@Override
	public ContactUsDto updateContactUsInquiryByContactUsId(@Valid ContactUsDto contactUsDto, @Valid Long contactUsId) {

		if (contactUsId == null)
			throw new IllegalArgumentException("Contact us id cannot be null");

		ContactUs contactUs = this.contactUsRepository.findById(contactUsId).orElseThrow(
				() -> new ResourceNotFoundException("Contact Us", "contact us id", Long.toString(contactUsId)));

		contactUs.setFullName(contactUsDto.getFullName());
		contactUs.setMobileNumber(contactUsDto.getMobileNumber());
		contactUs.setEmail(contactUsDto.getEmail());
		contactUs.setSubject(contactUsDto.getSubject());
		contactUs.setMessage(contactUsDto.getMessage());
		contactUs.setModifiedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		contactUs.setModifiedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		ContactUs updatedContactUs = this.contactUsRepository.save(contactUs);

		return this.mapToContactUsDto(updatedContactUs);

	}

	// delete single contact us inquiry by contact us id
	@Override
	public void deleteSingleContactUsInquiryByContactUsId(Long contactUsId) {

		if (contactUsId == null)
			throw new IllegalArgumentException("Contact us id cannot be null");

		ContactUs inquiry = this.contactUsRepository.findById(contactUsId).orElseThrow(
				() -> new ResourceNotFoundException("Contact us inquiry", "contact us id", Long.toString(contactUsId)));

		this.contactUsRepository.delete(inquiry);

	}

	// delete list of contact us inquiry by customer id
	@Override
	public void deleteListOfContactUsInquiriesByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id cannot be null");

		Customers customer = this.customersRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "contact us id", customerId.toString()));

		List<ContactUs> contactUsInquiries = this.contactUsRepository.findByCustomers(customer);

		if (contactUsInquiries.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "customer id", String.valueOf(customerId));

		contactUsInquiries.stream().forEach((i) -> this.contactUsRepository.delete(i));

	}

	// delete all contact us inquiries
	@Override
	public void deleteAllContactUsInquiries() {

		this.contactUsRepository.deleteAll();

	}

	// get single contact us inquiry by contact us id
	@Override
	public ContactUsDto getSingleContactUsInquiryByContactUsId(Long contactUsId) {

		if (contactUsId == null)
			throw new IllegalArgumentException("Contact us id cannot be null");

		ContactUs contactUs = this.contactUsRepository.findById(contactUsId).orElseThrow(
				() -> new ResourceNotFoundException("Contact us inquiry", "contact us id", Long.toString(contactUsId)));

		return this.mapToContactUsDto(contactUs);

	}

	// get all contact us inquiries
	@Override
	public List<ContactUsDto> getAllContactUsInquiries() {

		List<ContactUs> contactUsList = this.contactUsRepository.findAll();

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "", "");

		return contactUsList
				.stream().sorted(Comparator.comparing(ContactUs::getModifiedDate)
						.thenComparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get list of contact us inquiries by customer id
	@Override
	public List<ContactUsDto> getListOfContactUsInquiriesByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id cannot be null");

		Customers customer = this.customersRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", customerId.toString()));

		List<ContactUs> findByCustomers = this.contactUsRepository.findByCustomers(customer);

		if (findByCustomers.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "customer id", String.valueOf(customerId));

		return findByCustomers.stream()
				.sorted(Comparator.comparing(ContactUs::getModifiedDate).reversed()
						.thenComparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get list of contact us inquiries by mobile number
	@Override
	public List<ContactUsDto> getListOfContactUsInquiriesByMobileNumber(String mobileNumber) {

		if (mobileNumber.isBlank())
			throw new IllegalArgumentException("Mobile number cannot be blank");

		List<ContactUs> contactUsList = this.contactUsRepository.findByMobileNumber(mobileNumber);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "Mobile Number", mobileNumber);

		return contactUsList.stream()
				.sorted(Comparator.comparing(ContactUs::getModifiedDate).reversed()
						.thenComparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get list of contact us inquiries by date
	@Override
	public List<ContactUsDto> getListOfContactUsInquiriesByDate(LocalDate modifiedDate) {

		if (modifiedDate == null)
			throw new IllegalArgumentException("Date must be provided.");

		List<ContactUs> contactUsList = this.contactUsRepository.findByModifiedDate(modifiedDate);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "date", String.valueOf(modifiedDate));

		return contactUsList.stream().sorted(Comparator.comparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get list of contact us inquiries by range of date
	@Override
	public List<ContactUsDto> getListOfContactUsInquiriesByRangeOfDate(LocalDate startDate, LocalDate endDate) {

		if (startDate == null || endDate == null)
			throw new IllegalArgumentException("Start date and end date must be provided.");

		List<ContactUs> contactUsList = this.contactUsRepository.findByModifiedDateBetween(startDate, endDate);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "start date",
					String.valueOf(startDate) + " and end date : " + String.valueOf(endDate));

		return contactUsList
				.stream().sorted(Comparator.comparing(ContactUs::getModifiedDate)
						.thenComparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get list of contact us inquiries by date before
	@Override
	public List<ContactUsDto> getListOfContactUsInquiriesBeforeDate(LocalDate modifiedDate) {

		if (modifiedDate == null)
			throw new IllegalArgumentException("Date must be provided.");

		List<ContactUs> contactUsList = this.contactUsRepository.findByModifiedDateBefore(modifiedDate);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "date before", String.valueOf(modifiedDate));

		return contactUsList.stream()
				.sorted(Comparator.comparing(ContactUs::getModifiedDate).reversed()
						.thenComparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get list of contact us inquiries by date after
	@Override
	public List<ContactUsDto> getListOfContactUsInquiriesAfterDate(LocalDate modifiedDate) {

		if (modifiedDate == null)
			throw new IllegalArgumentException("Date must be provided.");

		List<ContactUs> contactUsList = this.contactUsRepository.findByModifiedDateAfter(modifiedDate);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "date after", String.valueOf(modifiedDate));

		return contactUsList.stream()
				.sorted(Comparator.comparing(ContactUs::getModifiedDate).thenComparing(ContactUs::getModifiedTime))
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	private ContactUsDto mapToContactUsDto(ContactUs contactUs) {
		return this.modelMapper.map(contactUs, ContactUsDto.class);
	}

	private ContactUs mapToContactUs(ContactUsDto contactUsDto) {
		return this.modelMapper.map(contactUsDto, ContactUs.class);
	}

}
