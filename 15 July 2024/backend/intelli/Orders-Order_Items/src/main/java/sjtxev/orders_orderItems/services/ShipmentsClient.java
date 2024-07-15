package sjtxev.orders_orderItems.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SHIPMENTS-SERVICE", path = "/shipments")
public interface ShipmentsClient {

	@GetMapping("/find/existsSingleShipmentByShipmentIdAndCustomerId/shipmentId/{shipmentId}/customerId/{customerId}")
	public Boolean existsSingleShipmentByShipmentIdAndCustomerId(@PathVariable("shipmentId") Long shipmentId,
			@PathVariable("customerId") Long customerId);

}
