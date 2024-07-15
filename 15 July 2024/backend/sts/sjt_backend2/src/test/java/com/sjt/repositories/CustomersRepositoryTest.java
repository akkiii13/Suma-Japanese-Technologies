package com.sjt.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import com.sjt.entities.Customers;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomersRepositoryTest {

	@Autowired
	private CustomersRepository customersRepository;

	@Test
	@Rollback(false)
	public void testSaveCustomer() {
		Customers customer = new Customers();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setMobileNumber("9876543210");
		customer.setEmail("john@doe.com");
		customer.setPassword("JohnDoe@9876543210");
		customer.setIsActive(true);
		Customers savedCustomer = customersRepository.save(customer);

		Customers customer1 = new Customers();
		customer1.setFirstName("b");
		customer1.setLastName("b");
		customer1.setMobileNumber("6000000002");
		customer1.setEmail("b@b.b");
		customer1.setPassword("BbBb2222@");
		customer1.setIsActive(false);

		Customers savedCustomer1 = customersRepository.save(customer1);
		customersRepository.flush();
		assertNotNull(savedCustomer.getCustomerId());
		assertNotNull(savedCustomer1.getCustomerId());
	}

	@Test
	public void testFindCustomerById() {
		Long customerId = 2L; // Assuming customer with ID 2 exists in your test database
		Optional<Customers> customerOptional = customersRepository.findById(customerId);
		assertTrue(customerOptional.isPresent());

		Customers customer = customerOptional.get();
		assertEquals("b", customer.getFirstName());
		assertEquals("b", customer.getLastName());
		assertEquals("6000000002", customer.getMobileNumber());
		assertEquals("b@b.b", customer.getEmail());
		assertEquals("BbBb2222@", customer.getPassword());
		assertTrue(customer.getIsActive());
	}

	@Test
	public void testFindByMobileNumber() {
		String mobileNumber = "6000000002"; // Replace with a valid mobile number

		Customers foundCustomer = customersRepository.findByMobileNumber(mobileNumber);
		assertNotNull(foundCustomer);
//		assertEquals(mobileNumber, foundCustomer.getMobileNumber());
	}

	@Test
	public void testExistsByMobileNumber() {
		String mobileNumber = "9876543210"; // Replace with a valid mobile number

		boolean existsByMobileNumber = customersRepository.existsByMobileNumberAndIsActive(mobileNumber, true);
		assertNotNull(existsByMobileNumber);
		assertTrue(existsByMobileNumber);
	}

	@Test
	public void testFindByIsActiveTrue() {
		List<Customers> activeCustomers = customersRepository.findByIsActiveTrue();
		assertNotNull(activeCustomers);
		assertTrue(activeCustomers.size() > 0);
	}

	@Test
	public void testFindByIsActiveFalse() {
		List<Customers> inactiveCustomers = customersRepository.findByIsActiveFalse();
		assertNotNull(inactiveCustomers);
		assertTrue(inactiveCustomers.size() > 0);
	}

	@Test
	public void testFindByCustomerIdAndIsActiveTrue() {
		Long customerId = 1L; // Assuming customer with ID 2 exists in your test database
		Optional<Customers> findByCustomerIdAndIsActiveTrue = customersRepository
				.findByCustomerIdAndIsActiveTrue(customerId);
		assertTrue(findByCustomerIdAndIsActiveTrue.isPresent());
	}

	@Test
	public void testFindByMobileNumberAndPassword() {
		String mobileNumber = "";
		String password = "";
		Customers findByMobileNumberAndPassword = customersRepository.findByMobileNumberAndPassword(mobileNumber,
				password);
		assertNotNull(findByMobileNumberAndPassword);
	}

	@Test
	@Rollback(false)
	public void testUpdateCustomer() {
		Long customerId = 2L;
		Optional<Customers> customerOptional = customersRepository.findById(customerId);
		assertTrue(customerOptional.isPresent());

		Customers customer = customerOptional.get();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setMobileNumber("9876543210");
		customer.setEmail("john@doe.com");
		customer.setPassword("JohnDoe@9876543210");
		customer.setIsActive(true);

		Customers savedCustomer = customersRepository.save(customer);
		customersRepository.flush();
		assertNotNull(savedCustomer.getCustomerId());
	}

}
