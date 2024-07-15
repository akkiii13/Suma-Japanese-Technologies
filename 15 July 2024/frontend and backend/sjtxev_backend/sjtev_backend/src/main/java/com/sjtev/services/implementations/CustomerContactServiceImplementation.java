package com.sjtev.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtev.entities.CustomerContact;
import com.sjtev.entities.Inquiry;
import com.sjtev.entities.helper.CustomerContactPrimaryKey;
import com.sjtev.enums.Category;
import com.sjtev.exceptions.ResourceNotFoundException;
import com.sjtev.payloads.CustomerContactDto;
import com.sjtev.repositories.CustomerContactRepository;
import com.sjtev.repositories.InquiryRepository;
import com.sjtev.services.CustomerContactService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerContactServiceImplementation implements CustomerContactService {

	@Autowired
	private CustomerContactRepository customerContactRepository;

	@Autowired
	private InquiryRepository inquiryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CustomerContactDto saveCustomerContact(CustomerContactDto customerContactDto, Long inquiryId) {

		this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId)));

		CustomerContact customerContact = this.mapToCustomerContact(customerContactDto);

		Long maxCustomerContactId = customerContactRepository.findMaxCustomerContactIdByInquiryId(inquiryId);
		Long nextCustomerContactId = (maxCustomerContactId != null) ? maxCustomerContactId + 1 : 1;

		CustomerContactPrimaryKey customerContactPrimaryKey = new CustomerContactPrimaryKey();
		customerContactPrimaryKey.setCustomerContactIdPK(nextCustomerContactId);
		customerContactPrimaryKey.setInquiryIdPK(inquiryId);

		customerContact.setCustomerContactId(customerContactPrimaryKey);
		customerContact.setCreatedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		customerContact.setCreatedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		if (!customerContact.getNeedToEscalate())
			customerContact.setEscalationMessage(null);

		CustomerContact savedCustomerContact = this.customerContactRepository.save(customerContact);

		return this.mapToCustomerContactDto(savedCustomerContact);

	}

	@Override
	public CustomerContactDto updateCustomerContact(CustomerContactDto customerContactDto, Long customerContactId,
			Long inquiryId) {

		if (customerContactId == null)
			throw new IllegalArgumentException("Customer contact id can not be null");
		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		CustomerContactPrimaryKey customerContactPrimaryKey = new CustomerContactPrimaryKey();
		customerContactPrimaryKey.setCustomerContactIdPK(customerContactId);
		customerContactPrimaryKey.setInquiryIdPK(inquiryId);

		CustomerContact customerContact = this.customerContactRepository.findById(customerContactPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Customer contact", "inuiry id",
						String.valueOf(inquiryId) + " and customer contact id : " + Long.toString(customerContactId)));

		customerContact.setCategory(customerContactDto.getCategory());
		customerContact.setCustomerAnswer(customerContactDto.getCustomerAnswer());
		customerContact.setNeedToEscalate(customerContactDto.getNeedToEscalate());
		customerContact.setCreatedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		customerContact.setCreatedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		if (customerContact.getNeedToEscalate() == false)
			customerContact.setEscalationMessage(null);

		CustomerContact savedCustomerContact = this.customerContactRepository.save(customerContact);

		return this.mapToCustomerContactDto(savedCustomerContact);

	}

	@Override
	public void deleteSingleCustomerContactByCustomerContactIdAndInquiryId(Long customerContactId, Long inquiryId) {

		if (customerContactId == null)
			throw new IllegalArgumentException("Customer contact id can not be null");
		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		CustomerContactPrimaryKey customerContactPrimaryKey = new CustomerContactPrimaryKey();
		customerContactPrimaryKey.setCustomerContactIdPK(customerContactId);
		customerContactPrimaryKey.setInquiryIdPK(inquiryId);

		if (!(this.customerContactRepository.existsById(customerContactPrimaryKey)))
			throw new ResourceNotFoundException("Customer contact", "inuiry id",
					String.valueOf(inquiryId) + " and customer contact id : " + Long.toString(customerContactId));

		this.customerContactRepository.deleteById(customerContactPrimaryKey);

	}

	@Override
	public void deleteListOfCustomerContactByInquiryId(Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		Inquiry inquiry = this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId)));

		List<CustomerContact> listOfCustomerContacts = this.customerContactRepository.findByInquiry(inquiry);

		listOfCustomerContacts.stream().forEach(i -> this.customerContactRepository.delete(i));

	}

	@Override
	public void deleteAllCustomerContacts() {
		this.customerContactRepository.deleteAll();
	}

	@Override
	public CustomerContactDto getSingleCustomerContactByCustomerContactIdAndInquiryId(Long customerContactId,
			Long inquiryId) {

		if (customerContactId == null)
			throw new IllegalArgumentException("Customer contact id can not be null");
		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		CustomerContactPrimaryKey customerContactPrimaryKey = new CustomerContactPrimaryKey();
		customerContactPrimaryKey.setCustomerContactIdPK(customerContactId);
		customerContactPrimaryKey.setInquiryIdPK(inquiryId);

		CustomerContact customerContact = this.customerContactRepository.findById(customerContactPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Customer contact", "inuiry id",
						String.valueOf(inquiryId) + " and customer contact id : " + Long.toString(customerContactId)));

		return this.mapToCustomerContactDto(customerContact);

	}

	@Override
	public List<CustomerContactDto> getAllCustomerContacts() {

		List<CustomerContact> findAll = this.customerContactRepository.findAll();
		return findAll.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	@Override
	public List<CustomerContactDto> searchByCategory(String category) {

		if (category.isBlank())
			throw new IllegalArgumentException("Please select category");

		List<CustomerContact> searchByCategory = this.customerContactRepository
				.searchByCategory(Category.valueOf(category));

		return searchByCategory.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	@Override
	public List<CustomerContactDto> searchByNeedToEscalate(Boolean needToEscalate) {

		if (needToEscalate == null)
			throw new IllegalArgumentException("This field can not be null.");

		List<CustomerContact> list = this.customerContactRepository.searchByNeedToEscalate(needToEscalate);

		return list.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	@Override
	public List<CustomerContactDto> getListOfInquiriesByDateBefore(LocalDate date) {

		if (date == null)
			throw new IllegalArgumentException("Date can not be null");

		List<CustomerContact> list = this.customerContactRepository.findByCreatedDateBefore(date);

		if (list == null)
			throw new ResourceNotFoundException("Customer contacts", "before date", date.toString());

		return list.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	@Override
	public List<CustomerContactDto> getListOfInquiriesBySpecificDate(LocalDate specificDate) {

		if (specificDate == null)
			throw new IllegalArgumentException("Date can not be null");

		List<CustomerContact> list = this.customerContactRepository.findByCreatedDate(specificDate);

		if (list == null)
			throw new ResourceNotFoundException("Customer contacts", "on specific date", specificDate.toString());

		return list.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	@Override
	public List<CustomerContactDto> getListOfInquiriesByRangeOfDates(LocalDate startDate, LocalDate endDate) {

		if (startDate == null)
			throw new IllegalArgumentException("Start date can not be null");
		if (endDate == null)
			throw new IllegalArgumentException("End date can not be null");

		List<CustomerContact> list = this.customerContactRepository.findByCreatedDateBetween(startDate, endDate);

		if (list == null)
			throw new ResourceNotFoundException("Customer contacts", "start date",
					startDate.toString() + " end date : " + endDate.toString());

		return list.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	@Override
	public List<CustomerContactDto> getListOfInquiriesByDateAfter(LocalDate date) {

		if (date == null)
			throw new IllegalArgumentException("Date can not be null");

		List<CustomerContact> list = this.customerContactRepository.findByCreatedDateAfter(date);

		if (list == null)
			throw new ResourceNotFoundException("Customer contacts", "after date", date.toString());

		return list.stream().map(this::mapToCustomerContactDto).collect(Collectors.toList());

	}

	private CustomerContactDto mapToCustomerContactDto(CustomerContact customerContact) {
		return this.modelMapper.map(customerContact, CustomerContactDto.class);
	}

	private CustomerContact mapToCustomerContact(CustomerContactDto customerContactDto) {
		return this.modelMapper.map(customerContactDto, CustomerContact.class);
	}

}
