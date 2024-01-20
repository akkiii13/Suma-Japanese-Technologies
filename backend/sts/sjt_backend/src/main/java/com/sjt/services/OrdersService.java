package com.sjt.services;

import java.util.List;

import com.sjt.payloads.OrdersDto;

import jakarta.validation.Valid;

public interface OrdersService {

	// place order
	OrdersDto placeOrder(@Valid OrdersDto ordersDto, Long customerId);

	// update order by order id and customer id
	OrdersDto updateOrderByOrderIdAndCustomerId(@Valid OrdersDto ordersDto, Long orderId, Long customerId);

	// delete single order by order id and customer id
	void deleteSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

	// delete list of orders by customer id
	void deleteListOfOrdersByCustomerId(Long customerId);

	// get all orders
	List<OrdersDto> getAllOrders();

	// get single order by order id and customer id
	OrdersDto getSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

	// get list of orders by customer id
	List<OrdersDto> getListOfOrdersByCustomerId(Long customerId);

}
