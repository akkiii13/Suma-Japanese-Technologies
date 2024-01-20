package sjtxev.orders_orderItems.services;

import java.util.List;

import sjtxev.orders_orderItems.payloads.OrderDto;

import jakarta.validation.Valid;

public interface OrderService {

	// place order
	OrderDto placeOrder(@Valid OrderDto ordersDto, Long customerId);

	// update order by order id and customer id
	OrderDto updateOrderByOrderIdAndCustomerId(@Valid OrderDto ordersDto, Long orderId, Long customerId);

	// delete single order by order id and customer id
	void deleteSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

	// delete list of orders by customer id
	void deleteListOfOrdersByCustomerId(Long customerId);

	// get all orders
	List<OrderDto> getAllOrders();

	// get single order by order id and customer id
	OrderDto getSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

	// get list of orders by customer id
	List<OrderDto> getListOfOrdersByCustomerId(Long customerId);

}
