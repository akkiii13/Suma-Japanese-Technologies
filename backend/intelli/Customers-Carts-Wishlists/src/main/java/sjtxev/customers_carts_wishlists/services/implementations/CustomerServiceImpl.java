package sjtxev.customers_carts_wishlists.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sjtxev.customers_carts_wishlists.entities.Customer;
import sjtxev.customers_carts_wishlists.exceptions.ResourceNotFoundException;
import sjtxev.customers_carts_wishlists.payloads.CustomerDto;
import sjtxev.customers_carts_wishlists.repositories.CustomerRepository;
import sjtxev.customers_carts_wishlists.services.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ModelMapper modelMapper;

	// save new customer
	@Override
	public CustomerDto createNewCustomer(CustomerDto customerDto) {

		if (this.customerRepository.existsByMobileNumberAndIsActive(customerDto.getMobileNumber(), true))
			throw new IllegalArgumentException("This mobile number is already registered.");

		Customer customers = null;

		if (this.customerRepository.existsByMobileNumberAndIsActive(customerDto.getMobileNumber(), false)) {

			customers = this.customerRepository.findByMobileNumber(customerDto.getMobileNumber());

			customers.setEmail(customerDto.getEmail());
			customers.setFirstName(customerDto.getFirstName());
			customers.setLastName(customerDto.getLastName());
			customers.setMobileNumber(customerDto.getMobileNumber());
//          customers.setPassword(this.passwordEncoder.encode(customerDto.getPassword()));
			customers.setPassword(customerDto.getPassword());

		} else
			customers = this.mapToCustomer(customerDto);

		customers.setIsActive(true);

		Customer savedCustomer = this.customerRepository.save(customers);

		return this.mapToCustomerDto(savedCustomer);

	}

	// update customer by customer id
	@Override
	public CustomerDto updateCustomerByCustomerId(CustomerDto customerDto, Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customer customers = this.customerRepository.findById(customerId).filter(Customer::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		Customer findByMobileNumber = this.customerRepository.findByMobileNumber(customerDto.getMobileNumber());

		if (findByMobileNumber != null)
			if (findByMobileNumber.getCustomerId() != customerId && findByMobileNumber.getIsActive() == true)
				throw new IllegalArgumentException(
						"This mobile number is already registered with another account. Please enter another number or try loggig in");

		customers.setEmail(customerDto.getEmail());
		customers.setFirstName(customerDto.getFirstName());
		customers.setLastName(customerDto.getLastName());
		customers.setMobileNumber(customerDto.getMobileNumber());
		customers.setPassword(customerDto.getPassword());
		customers.setIsActive(customerDto.getIsActive());

		Customer updatedCustomer = this.customerRepository.save(customers);

		return this.mapToCustomerDto(updatedCustomer);

	}

	// delete single customer by customer id
	@Override
	public void deleteSingleCustomerByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customer customer = this.customerRepository.findById(customerId).filter(Customer::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		customer.setIsActive(false);

		this.customerRepository.save(customer);

	}

	// get single customer by customer id
	@Override
	public CustomerDto getSingleCustomerByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customer customers = this.customerRepository.findById(customerId).filter(Customer::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		return this.mapToCustomerDto(customers);

	}

	// get list of customers by any value
	@Override
	public List<CustomerDto> getListOfCustomersByAnyValue(Long customerId, String firstName, String lastName,
			String email, String mobileNumber) {

		if (customerId == null || firstName.isBlank())
			throw new IllegalArgumentException("value cannot be null or empty");

		List<Customer> customers = this.customerRepository
				.findByCustomerIdOrFirstNameContainingOrLastNameContainingOrEmailOrMobileNumber(customerId, firstName,
						lastName, email, mobileNumber);

		if (customers.isEmpty())
			throw new ResourceNotFoundException("Customer", "value", firstName);

		return customers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());

	}

	// get all customers
	@Override
	public List<CustomerDto> getAllCustomers() {

		List<Customer> customers = this.customerRepository.findAll();

		if (customers.isEmpty())
			throw new ResourceNotFoundException("Customers", "", "");

		return customers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());

	}

	// get list of active customers
	@Override
	public List<CustomerDto> getAllActiveCustomers() {

		List<Customer> activeCustomers = this.customerRepository.findByIsActiveTrue();

		if (activeCustomers.isEmpty())
			throw new ResourceNotFoundException("Customers", "status isActive", "true");

		return activeCustomers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());

	}

	// get list of inactive customers
	@Override
	public List<CustomerDto> getAllInactiveCustomers() {

		List<Customer> inactiveCustomers = this.customerRepository.findByIsActiveFalse();

		if (inactiveCustomers.isEmpty())
			throw new ResourceNotFoundException("Customers", "status isActive", "false");

		return inactiveCustomers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());

	}

	// get single customer by mobile number
	@Override
	public CustomerDto getByMobileNumber(String mobileNumber) {

		if (mobileNumber.isBlank())
			throw new IllegalArgumentException("Mobile number cannot be blank");

		Customer customer = this.customerRepository.findByMobileNumber(mobileNumber);

		if (customer != null && customer.getIsActive() == true)
			return this.mapToCustomerDto(customer);

		return null;

	}

	// exists single customer by customer id and is active true
	@Override
	public Boolean existsByCustomerIdAndIsActiveTrue(Long customerId) {

		return this.customerRepository.existsByCustomerIdAndIsActive(customerId, true);

	}

	// exists single customer by mobile number and is active true
	@Override
	public Boolean existsByMobileNumberAndIsActiveTrue(String mobileNumber) {

		return this.customerRepository.existsByMobileNumberAndIsActive(mobileNumber, true);

	}

	private CustomerDto mapToCustomerDto(Customer customers) {
		return this.modelMapper.map(customers, CustomerDto.class);
	}

	private Customer mapToCustomer(CustomerDto customerDto) {
		return this.modelMapper.map(customerDto, Customer.class);
	}

}
