package sjtxev.payments.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sjtxev.payments.payloads.PaymentDto;
import sjtxev.payments.responseObjects.ApiResponse;
import sjtxev.payments.services.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		super();
	}

	// make payment
	@PostMapping("/save/{customerId}")
	public ResponseEntity<PaymentDto> makePayment(@Valid @RequestBody PaymentDto paymentDto,
			@Valid @PathVariable(name = "customerId") Long customerId) {
		return new ResponseEntity<PaymentDto>(this.paymentService.makePayment(paymentDto, customerId),
				HttpStatus.CREATED);
	}

	// update payment by payment id and customer id
	@PutMapping("/update/paymentId/{paymentId}/customerId/{customerId}")
	public ResponseEntity<PaymentDto> updatePaymentByPaymentIdAndCustomerId(@Valid @RequestBody PaymentDto paymentDto,
			@PathVariable("paymentId") Long paymentId, @PathVariable("customerId") Long customerId) {
		return ResponseEntity
				.ok(this.paymentService.updatePaymentByPaymentIdAndCustomerId(paymentDto, paymentId, customerId));
	}

	// delete single payment by payment id and customer id
	@DeleteMapping("/delete/deleteSinglePaymentByPaymentIdAndCustomerId/paymentId/{paymentId}/customerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteSinglePaymentByPaymentIdAndCustomerId(
			@PathVariable("paymentId") Long paymentId, @PathVariable("customerId") Long customerId) {
		this.paymentService.deleteSinglePaymentByPaymentIdAndCustomerId(paymentId, customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Payment deleted successfully", true), HttpStatus.OK);
	}

	// delete list of payments by customer id
	@DeleteMapping("/delete/deleteListOfPaymentsByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteListOfPaymentsByCustomerId(@PathVariable("customerId") Long customerId) {
		this.paymentService.deleteListOfPaymentsByCustomerId(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("All payments are deleted successfully", true),
				HttpStatus.OK);
	}

	// get all payments
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		return ResponseEntity.ok(this.paymentService.getAllPayments());
	}

	// get list of payments by customer id
	@GetMapping("/getAllPaymentsByCustomerId/{customerId}")
	public ResponseEntity<List<PaymentDto>> getAllPaymentsByCustomerId(@PathVariable("customerId") Long customerId) {
		List<PaymentDto> paymentListByCustomerId = this.paymentService.getAllPaymentsByCustomerId(customerId);
		return new ResponseEntity<List<PaymentDto>>(paymentListByCustomerId, HttpStatus.OK);
	}

	// get single payment by payment id and customer id
	@GetMapping("/getSinglePaymentByPaymentIdAndCustomerId/paymentId/{paymentId}/customerId/{customerId}")
	public ResponseEntity<PaymentDto> getSinglePaymentByPaymentIdAndCustomerId(
			@PathVariable("paymentId") Long paymentId, @PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(this.paymentService.getSinglePaymentByPaymentIdAndCustomerId(paymentId, customerId));
	}

	// exists single payment by payment id and customer id
	@GetMapping("/find/existsSinglePaymentByPaymentIdAndCustomerId/paymentId/{paymentId}/customerId/{customerId}")
	public Boolean existsSinglePaymentByPaymentIdAndCustomerId(@PathVariable("paymentId") Long paymentId,
			@PathVariable("customerId") Long customerId) {
		return this.paymentService.existsSinglePaymentByPaymentIdAndCustomerId(paymentId, customerId);
	}

}
