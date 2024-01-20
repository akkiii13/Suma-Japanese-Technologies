package com.sjt.services;

import java.util.List;

import com.sjt.payloads.PaymentDto;

import jakarta.validation.Valid;

public interface PaymentService {

	// make payment
	PaymentDto makePayment(@Valid PaymentDto paymentDto, Long customerId);

	// update payment by payment id and customer id
	PaymentDto updatePaymentByPaymentIdAndCustomerId(@Valid PaymentDto paymentDto, Long paymentId, Long customerId);

	// delete single payment by payment id and customer id
	void deleteSinglePaymentByPaymentIdAndCustomerId(Long paymentId, Long customerId);

	// delete list of payments by customer id
	void deleteListOfPaymentsByCustomerId(Long customerId);

	// get all payments
	List<PaymentDto> getAllPayments();

	// get list of payments by customer id
	List<PaymentDto> getAllPaymentsByCustomerId(Long customerId);

	// get single payment by payment id and customer id
	PaymentDto getSinglePaymentByPaymentIdAndCustomerId(Long paymentId, Long customerId);

}
