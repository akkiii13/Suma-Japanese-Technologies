package sjtxev.orders_orderItems.services;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import sjtxev.orders_orderItems.payloads.OrderItemDto;

import jakarta.validation.Valid;

public interface OrderItemService {

	// save orderItem
	OrderItemDto saveOrderItem(@Valid @RequestBody OrderItemDto orderItemDto);

	// update orderItem by orderItem id
	OrderItemDto updateOrderItemByOrderItemId(@Valid @RequestBody OrderItemDto orderItemDto, Long orderItemId);

	// delete single orderItem by orderItem id
	void deleteSingleOrderItemByOrderItemIdAndOrderId(Long orderItemId, Long orderId);

	// delete list of orderItems by order id
	void deleteListOfOrderItemsByOrderId(Long orderId);

	// get all orderItems
	List<OrderItemDto> getAllOrderItems();

	// get list of orderItems by order id
	List<OrderItemDto> getListOfOrderItemsByOrderId(Long orderId);

	// get single orderItem by orderItem id and order id
	OrderItemDto getSingleOrderItemByOrderItemIdAndOrderId(Long orderItemId, Long orderId);

}
