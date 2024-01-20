package com.sjt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjt.payloads.OrderItemDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.OrderItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderItem")
@Validated
public class OrderItemController {

	@Autowired
	OrderItemService orderItemService;

	// save orderItem
	@PostMapping("/save")
	public ResponseEntity<OrderItemDto> saveOrderItem(@Valid @RequestBody OrderItemDto orderItemDto) {
		return new ResponseEntity<OrderItemDto>(this.orderItemService.saveOrderItem(orderItemDto), HttpStatus.CREATED);
	}

	// update orderItem by orderItem id
	@PutMapping("/update/{orderItemId}")
	public ResponseEntity<OrderItemDto> updateOrderItemByOrderItemId(@Valid @RequestBody OrderItemDto orderItemDto,
			@PathVariable Long orderItemId) {
		return new ResponseEntity<OrderItemDto>(
				this.orderItemService.updateOrderItemByOrderItemId(orderItemDto, orderItemId), HttpStatus.OK);
	}

	// delete single orderItem by orderItem id
	@DeleteMapping("/delete/deleteSingleOrderItemByOrderItemIdAndOrderId/orderItemId/{orderItemId}/orderId/{orderId}")
	public ResponseEntity<ApiResponse> deleteSingleOrderItemByOrderItemIdAndOrderId(@PathVariable Long orderItemId,
			@PathVariable Long orderId) {
		this.orderItemService.deleteSingleOrderItemByOrderItemIdAndOrderId(orderItemId, orderId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order item deleted successfully", true), HttpStatus.OK);
	}

	// delete list of orderItems by order id
	@DeleteMapping("/delete/deleteListOfOrderItemsByOrderId/{orderId}")
	public ResponseEntity<ApiResponse> deleteListOfOrderItemsByOrderId(@PathVariable Long orderId) {
		this.orderItemService.deleteListOfOrderItemsByOrderId(orderId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order items deleted successfully", true),
				HttpStatus.OK);
	}

	// get all orderItems
	@GetMapping("/getAllOrderItems")
	public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
		return ResponseEntity.ok(this.orderItemService.getAllOrderItems());
	}

	// get list of orderItems by order id
	@GetMapping("/getListOfOrderItemsByOrderId/{orderId}")
	public ResponseEntity<List<OrderItemDto>> getListOfOrderItemsByOrderId(@PathVariable("orderId") Long orderId) {
		List<OrderItemDto> orderItemDtos = this.orderItemService.getListOfOrderItemsByOrderId(orderId);
		return new ResponseEntity<List<OrderItemDto>>(orderItemDtos, HttpStatus.OK);
	}

	// get single orderItem by orderItem id and order id
	@GetMapping("/getSingleOrderItemByOrderItemIdAndOrderId/orderItemId/{orderItemId}/orderId/{orderId}")
	public ResponseEntity<OrderItemDto> getSingleOrderItemByOrderItemIdAndOrderId(
			@PathVariable("orderItemId") Long orderItemId, @PathVariable("orderId") Long orderId) {
		return ResponseEntity.ok(this.orderItemService.getSingleOrderItemByOrderItemIdAndOrderId(orderItemId, orderId));
	}

}
