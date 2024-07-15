package sjtxev.orders_orderItems.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENTS-SERVICE", path = "/payments")
public interface PaymentsClient {

	@GetMapping("/find/existsSinglePaymentByPaymentIdAndCustomerId/paymentId/{paymentId}/customerId/{customerId}")
	public Boolean existsSinglePaymentByPaymentIdAndCustomerId(@PathVariable("paymentId") Long paymentId,
			@PathVariable("customerId") Long customerId);

}
