package sjtxev.customers_carts_wishlists.controllers;

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
import sjtxev.customers_carts_wishlists.payloads.CartDto;
import sjtxev.customers_carts_wishlists.responseObjects.ApiResponse;
import sjtxev.customers_carts_wishlists.services.CartService;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CartController {

	@Autowired
	CartService cartService;

	// add to cart
	@PostMapping("/save/{customerId}")
	public ResponseEntity<CartDto> saveCartDetails(@Valid @RequestBody CartDto cartDto,
			@PathVariable(name = "customerId") Long customerId) {
		return new ResponseEntity<CartDto>(this.cartService.addToCart(cartDto, customerId), HttpStatus.CREATED);
	}

	// update cart by cart id and customer id
	@PutMapping("/update/cartId/{cartId}/customerId/{customerId}")
	public ResponseEntity<CartDto> updateCartByCartIdAndCustomerId(@Valid @RequestBody CartDto cartDto,
			@PathVariable("cartId") Long cartId, @PathVariable("customerId") Long customerId) {
		return new ResponseEntity<CartDto>(
				this.cartService.updateCartByCartIdAndCustomerId(cartDto, cartId, customerId), HttpStatus.OK);
	}

	// delete single cart by cart id and customer id
	@DeleteMapping("/delete/deleteSingleCartByCartIdAndCustomerId/cartId/{cartId}/customerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteSingleCartByCartIdAndCustomerId(@PathVariable("cartId") Long cartId,
			@PathVariable("customerId") Long customerId) {
		this.cartService.deleteSingleCartByCartIdAndCustomerId(cartId, customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Cart deleted successfully", true), HttpStatus.OK);
	}

	// get all carts
	@GetMapping("/get/getAllCarts")
	public ResponseEntity<List<CartDto>> getAllCarts() {
		return new ResponseEntity<List<CartDto>>(this.cartService.getAllCarts(), HttpStatus.OK);
	}

	// get single cart by cart id and customer id
	@GetMapping("/get/getSingleCartByCartIdAndCustomerId/cartId/{cartId}/customerId/{customerId}")
	public ResponseEntity<CartDto> getSingleCartByCartIdAndCustomerId(@PathVariable("cartId") Long cartId,
			@PathVariable("customerId") Long customerId) {
		return new ResponseEntity<CartDto>(this.cartService.getSingleCartByCartIdAndCustomerId(cartId, customerId),
				HttpStatus.OK);
	}

	// get list of carts by customer id
	@GetMapping("/get/getListOfCartsByCustomerId/{customerId}")
	public ResponseEntity<List<CartDto>> getListOfCartsByCustomerId(@PathVariable("customerId") Long customerId) {
		List<CartDto> cartListByCustomerId = this.cartService.getListOfCartsByCustomerId(customerId);
		return new ResponseEntity<List<CartDto>>(cartListByCustomerId, HttpStatus.OK);
	}

}
