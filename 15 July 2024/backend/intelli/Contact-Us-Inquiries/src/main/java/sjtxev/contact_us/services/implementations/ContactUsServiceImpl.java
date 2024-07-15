package sjtxev.contact_us.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sjtxev.contact_us.entities.ContactUs;
import sjtxev.contact_us.entities.Customer;
import sjtxev.contact_us.exceptions.ResourceNotFoundException;
import sjtxev.contact_us.payloads.ContactUsDto;
import sjtxev.contact_us.repositories.ContactUsRepository;
//import sjtxev.contact_us.repositories.CustomersRepository;
import sjtxev.contact_us.services.ContactUsService;
import sjtxev.contact_us.services.CustomersClient;

@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	ContactUsRepository contactUsRepository;

	@Autowired
	CustomersClient customersClient;

	@Autowired
	ModelMapper modelMapper;

	// save contact us inquiry
	@Override
	public ContactUsDto saveContactUsInquiry(@Valid ContactUsDto contactUsDto) {

		String mobileNumber = contactUsDto.getMobileNumber();

		ContactUs contactUs = this.mapToContactUs(contactUsDto);

		contactUs.setModifiedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		contactUs.setModifiedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		Customer customer = null;

		ResponseEntity<Customer> response = null;

		if (mobileNumber != null)
			response = this.customersClient.findByMobileNumber(mobileNumber);

		if (response.getStatusCode() == HttpStatus.OK)
			customer = response.getBody();

		if (customer != null)
			contactUs.setCustomerId(customer.getCustomerId());

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

		List<ContactUs> contactUsInquiries = this.contactUsRepository.findByCustomerId(customerId);

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

		List<ContactUs> findByCustomers = this.contactUsRepository.findByCustomerId(customerId);

		if (findByCustomers.isEmpty())
			throw new ResourceNotFoundException("Contact us inquiries", "customer id", String.valueOf(customerId));

		return findByCustomers.stream()
				.sorted(Comparator.comparing(ContactUs::getModifiedDate).reversed()
						.thenComparing(ContactUs::getModifiedTime).reversed())
				.map(this::mapToContactUsDto).collect(Collectors.toList());

	}

	// get single contact us inquiry by customer id and contact us id
	public ContactUsDto getSingleContactUsInquiryByCustomerIdAndContactUsId(Long customerId, Long contactUsId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id cannot be null");

		if (contactUsId == null)
			throw new IllegalArgumentException("Contact us id cannot be null");

		ContactUs contactUs = this.contactUsRepository.findByCustomerIdAndContactUsId(customerId, contactUsId);

		if (contactUs == null)
			throw new ResourceNotFoundException("Contact us inquiries", "customer id",
					String.valueOf(customerId) + " and contact us id : " + Long.toString(contactUsId));

		return this.mapToContactUsDto(contactUs);

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
