package com.sjt.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sjt.entities.Customers;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.CustomersDto;
import com.sjt.repositories.CustomersRepository;
import com.sjt.services.CustomersService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	ModelMapper modelMapper;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	// save new customer
	@Override
	public CustomersDto createNewCustomer(CustomersDto customersDto) {

		if (this.customersRepository.existsByMobileNumberAndIsActive(customersDto.getMobileNumber(), true))
			throw new IllegalArgumentException("This mobile number is already registered.");

		Customers customers = null;

		if (this.customersRepository.existsByMobileNumberAndIsActive(customersDto.getMobileNumber(), false)) {

			customers = this.customersRepository.findByMobileNumber(customersDto.getMobileNumber());

			customers.setEmail(customersDto.getEmail());
			customers.setFirstName(customersDto.getFirstName());
			customers.setLastName(customersDto.getLastName());
			customers.setMobileNumber(customersDto.getMobileNumber());
			customers.setPassword(customersDto.getPassword());
//			customers.setPassword(this.passwordEncoder.encode(customersDto.getPassword()));

		} else
			customers = this.mapToCustomers(customersDto);

		customers.setIsActive(true);

		Customers savedCustomer = this.customersRepository.save(customers);

		return this.mapToCustomersDto(savedCustomer);

	}

	// update customer by customer id
	@Override
	public CustomersDto updateCustomerByCustomerId(CustomersDto customersDto, Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findById(customerId).filter(Customers::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		boolean existsByMobileNumberAndIsActive = this.customersRepository
				.existsByMobileNumberAndIsActive(customersDto.getMobileNumber(), true);

		if (existsByMobileNumberAndIsActive)
			throw new IllegalArgumentException(
					"this mobile number is already registered with another account. Please enter another number or try loggig in");

		customers.setEmail(customersDto.getEmail());
		customers.setFirstName(customersDto.getFirstName());
		customers.setLastName(customersDto.getLastName());
		customers.setMobileNumber(customersDto.getMobileNumber());
		customers.setPassword(customersDto.getPassword());
		customers.setIsActive(customersDto.getIsActive());

		Customers updatedCustomer = this.customersRepository.save(customers);

		return this.mapToCustomersDto(updatedCustomer);

	}

	// delete single customer by customer id
	@Override
	public void deleteSingleCustomerByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findById(customerId).filter(Customers::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		customer.setIsActive(false);

		this.customersRepository.save(customer);

	}

	// get single customer by customer id
	@Override
	public CustomersDto getSingleCustomerByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findById(customerId).filter(Customers::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		return this.mapToCustomersDto(customers);

	}

	// get list of customers by any value
	@Override
	public List<CustomersDto> getListOfCustomersByAnyValue(Long customerId, String firstName, String lastName,
			String email, String mobileNumber) {

		if (customerId == null || firstName.isBlank())
			throw new IllegalArgumentException("value cannot be null or empty");

		List<Customers> customers = this.customersRepository
				.findByCustomerIdOrFirstNameContainingOrLastNameContainingOrEmailOrMobileNumber(customerId, firstName,
						lastName, email, mobileNumber);

		if (customers.isEmpty())
			throw new ResourceNotFoundException("Customers", "value", firstName);

		return customers.stream().map(this::mapToCustomersDto).collect(Collectors.toList());

	}

	// get all customers
	@Override
	public List<CustomersDto> getAllCustomers() {

		List<Customers> customers = this.customersRepository.findAll();

		if (customers.isEmpty())
			throw new ResourceNotFoundException("Customers", "", "");

		return customers.stream().map(this::mapToCustomersDto).collect(Collectors.toList());

	}

	// get list of active customers
	@Override
	public List<CustomersDto> getAllActiveCustomers() {

		List<Customers> activeCustomers = this.customersRepository.findByIsActiveTrue();

		if (activeCustomers.isEmpty())
			throw new ResourceNotFoundException("Customers", "status isActive", "true");

		return activeCustomers.stream().map(this::mapToCustomersDto).collect(Collectors.toList());

	}

	// get list of inactive customers
	@Override
	public List<CustomersDto> getAllInactiveCustomers() {

		List<Customers> inactiveCustomers = this.customersRepository.findByIsActiveFalse();

		if (inactiveCustomers.isEmpty())
			throw new ResourceNotFoundException("Customers", "status isActive", "false");

		return inactiveCustomers.stream().map(this::mapToCustomersDto).collect(Collectors.toList());

	}

	private CustomersDto mapToCustomersDto(Customers customers) {
		return this.modelMapper.map(customers, CustomersDto.class);
	}

	private Customers mapToCustomers(CustomersDto customersDto) {
		return this.modelMapper.map(customersDto, Customers.class);
	}

}
