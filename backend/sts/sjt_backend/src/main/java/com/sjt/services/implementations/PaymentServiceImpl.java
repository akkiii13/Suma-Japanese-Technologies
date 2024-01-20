package com.sjt.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjt.entities.Customers;
import com.sjt.entities.Payment;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.PaymentDto;
import com.sjt.repositories.CustomersRepository;
import com.sjt.repositories.PaymentRepository;
import com.sjt.services.PaymentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// make payment
	@Override
	public PaymentDto makePayment(@Valid PaymentDto paymentDto, Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		Payment payment = this.mapToPayment(paymentDto);

		payment.setPaymentDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		payment.setPaymentTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		payment.setCustomers(customer);

		Payment savedpayment = this.paymentRepository.save(payment);

		return this.mapToPaymentDto(savedpayment);

	}

	// update payment by payment id and customer id
	@Override
	public PaymentDto updatePaymentByPaymentIdAndCustomerId(@Valid PaymentDto paymentDto, Long paymentId,
			Long customerId) {

		if (paymentId == null)
			throw new IllegalArgumentException("Payment id must be provided.");

		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId)));

		payment.setPaymentDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		payment.setPaymentTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		payment.setAmount(paymentDto.getAmount());
		payment.setPaymentMethod(paymentDto.getPaymentMethod());

		Payment updatedPayment = this.paymentRepository.save(payment);

		return this.mapToPaymentDto(updatedPayment);

	}

	// delete single payment by payment id and customer id
	@Override
	public void deleteSinglePaymentByPaymentIdAndCustomerId(Long paymentId, Long customerId) {

		if (paymentId == null)
			throw new IllegalArgumentException("Payment id must be provided.");

		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId)));

		this.paymentRepository.delete(payment);

	}

	// delete list of payments by customer id
	@Override
	public void deleteListOfPaymentsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		List<Payment> findByCustomerId = this.paymentRepository.findByCustomers(customers);

		if (findByCustomerId.isEmpty())
			throw new ResourceNotFoundException("Payment", "customer id", String.valueOf(customerId));

		findByCustomerId.stream().forEach(wishlist -> this.paymentRepository.delete(wishlist));

	}

	// get all payments
	@Override
	public List<PaymentDto> getAllPayments() {

		List<Payment> paymentList = this.paymentRepository.findAll();

		if (paymentList.isEmpty())
			throw new ResourceNotFoundException("Payment list", "", "");

		return paymentList.stream().map(this::mapToPaymentDto).collect(Collectors.toList());

	}

	// get list of payments by customer id
	@Override
	public List<PaymentDto> getAllPaymentsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findById(customerId).filter(Customers::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", customerId.toString()));

		List<Payment> findByCustomer = this.paymentRepository.findByCustomers(customer);

		if (findByCustomer.isEmpty())
			throw new ResourceNotFoundException("Payment", "customer id", String.valueOf(customerId));

		return findByCustomer.stream().map(this::mapToPaymentDto).collect(Collectors.toList());

	}

	// get single payment by payment id and customer id
	@Override
	public PaymentDto getSinglePaymentByPaymentIdAndCustomerId(Long paymentId, Long customerId) {

		if (paymentId == null)
			throw new IllegalArgumentException("Payment ID must be provided.");

		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId)));

		return this.mapToPaymentDto(payment);

	}

	private PaymentDto mapToPaymentDto(Payment payment) {
		return this.modelMapper.map(payment, PaymentDto.class);
	}

	private Payment mapToPayment(PaymentDto paymentDto) {
		return this.modelMapper.map(paymentDto, Payment.class);
	}

}
