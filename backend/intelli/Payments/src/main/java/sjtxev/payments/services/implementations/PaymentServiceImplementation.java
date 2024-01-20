package sjtxev.payments.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sjtxev.payments.entities.Payment;
import sjtxev.payments.exceptions.ResourceNotFoundException;
import sjtxev.payments.payloads.PaymentDto;
import sjtxev.payments.repositories.PaymentRepository;
import sjtxev.payments.services.CustomersClient;
import sjtxev.payments.services.PaymentService;

@Service
@Transactional
public class PaymentServiceImplementation implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	CustomersClient customersClient;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// make payment
	@Override
	public PaymentDto makePayment(@Valid PaymentDto paymentDto, Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Boolean existsOrNot = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!existsOrNot)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		Payment payment = this.mapToPayment(paymentDto);

		payment.setPaymentDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		payment.setPaymentTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		payment.setCustomerId(customerId);

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

		Boolean existsOrNot = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!existsOrNot)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		List<Payment> findByCustomerId = this.paymentRepository.findByCustomerId(customerId);

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

		Boolean existsOrNot = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!existsOrNot)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		List<Payment> findByCustomer = this.paymentRepository.findByCustomerId(customerId);

		if (findByCustomer.isEmpty())
			throw new ResourceNotFoundException("Payment", "customer id", String.valueOf(customerId));

		return findByCustomer.stream().map(this::mapToPaymentDto).collect(Collectors.toList());

	}

	// get single payment by payment id and customer id
	@Override
	public PaymentDto getSinglePaymentByPaymentIdAndCustomerId(Long paymentId, Long customerId) {

		if (paymentId == null)
			throw new IllegalArgumentException("Payment ID must be provided.");

		Payment payment = this.paymentRepository.findByPaymentIdAndCustomerId(paymentId, customerId);

		if (payment == null)
			throw new ResourceNotFoundException("Payment", "payment id",
					Long.toString(paymentId) + " and customer id : " + String.valueOf(customerId));

		return this.mapToPaymentDto(payment);

	}

	// exists single payment by payment id and customer id
	@Override
	public Boolean existsSinglePaymentByPaymentIdAndCustomerId(Long paymentId, Long customerId) {

		return this.paymentRepository.existsByPaymentIdAndCustomerId(paymentId, customerId);

	}

	private PaymentDto mapToPaymentDto(Payment payment) {
		return this.modelMapper.map(payment, PaymentDto.class);
	}

	private Payment mapToPayment(PaymentDto paymentDto) {
		return this.modelMapper.map(paymentDto, Payment.class);
	}

}
