package com.sjt.services;

import java.util.List;

import com.sjt.payloads.CartDto;

import jakarta.validation.Valid;

public interface CartService {

	// add to cart
	CartDto addToCart(@Valid CartDto cartDto, Long customerId);

	// update cart by cart id and customer id
	CartDto updateCartByCartIdAndCustomerId(@Valid CartDto cartDto, Long cartId, Long customerId);

	// delete single cart by cart id and customer id
	void deleteSingleCartByCartIdAndCustomerId(Long cartId, Long customerId);

	// get all carts
	List<CartDto> getAllCarts();

	// get single cart by cart id and customer id
	CartDto getSingleCartByCartIdAndCustomerId(Long cartId, Long customerId);

	// get list of carts by customer id
	List<CartDto> getListOfCartsByCustomerId(Long customerId);

}
