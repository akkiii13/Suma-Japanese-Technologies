package com.sjt.controllers;

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

import com.sjt.payloads.OrdersDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.OrdersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:3000")
@Validated
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	public OrdersController(OrdersService ordersService) {
		super();
	}

	// place order
	@PostMapping("/save/{customerId}")
	public ResponseEntity<OrdersDto> placeOrder(@Valid @RequestBody OrdersDto ordersDto,
			@PathVariable(name = "customerId") Long customerId) {
		return new ResponseEntity<OrdersDto>(this.ordersService.placeOrder(ordersDto, customerId), HttpStatus.CREATED);
	}

	// update order by order id and customer id
	@PutMapping("/update/orderId/{orderId}/customerId/{customerId}")
	public ResponseEntity<OrdersDto> updateOrderByOrderIdAndCustomerId(@Valid @RequestBody OrdersDto ordersDto,
			@PathVariable(name = "orderId") Long orderId, @PathVariable(name = "customerId") Long customerId) {
		return new ResponseEntity<OrdersDto>(
				this.ordersService.updateOrderByOrderIdAndCustomerId(ordersDto, orderId, customerId), HttpStatus.OK);
	}

	// delete single order by order id and customer id
	@DeleteMapping("/delete/deleteSingleOrderByOrderIdAndCustomerId/orderId/{orderId}/customerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteSingleOrderByOrderIdAndCustomerId(
			@PathVariable(name = "orderId") Long orderId, @PathVariable(name = "customerId") Long customerId) {
		this.ordersService.deleteSingleOrderByOrderIdAndCustomerId(orderId, customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order deleted successfully", true), HttpStatus.OK);
	}

	// delete list of orders by customer id
	@DeleteMapping("/delete/deleteListOfOrdersByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteListOfOrdersByCustomerId(
			@PathVariable(name = "customerId") Long customerId) {
		this.ordersService.deleteListOfOrdersByCustomerId(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Orders deleted successfully", true), HttpStatus.OK);
	}

	// get all orders
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrdersDto>> getAllOrders() {
		return ResponseEntity.ok(this.ordersService.getAllOrders());
	}

	// get single order by order id and customer id
	@GetMapping("/getSingleOrderByOrderIdAndCustomerId/orderId/{orderId}/customerId/{customerId}")
	public ResponseEntity<OrdersDto> getSingleOrderByOrderIdAndCustomerId(@PathVariable("orderId") Long orderId,
			@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok(this.ordersService.getSingleOrderByOrderIdAndCustomerId(orderId, customerId));
	}

	// get list of orders by customer id
	@GetMapping("/getListOfOrdersByCustomerId/{customerId}")
	public ResponseEntity<List<OrdersDto>> getListOfOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
		List<OrdersDto> ordersListByCustomerId = this.ordersService.getListOfOrdersByCustomerId(customerId);
		return new ResponseEntity<List<OrdersDto>>(ordersListByCustomerId, HttpStatus.OK);
	}

}
