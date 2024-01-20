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

import com.sjt.payloads.WishlistDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.services.WishlistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wishlist")
@Validated
public class WishlistController {

	@Autowired
	WishlistService wishlistService;

	// add to wish list
	@PostMapping("/save/{customerId}")
	public ResponseEntity<WishlistDto> addToWishlist(@Valid @RequestBody WishlistDto wishlistDto,
			@Valid @PathVariable(name = "customerId") Long customerId) {
		return new ResponseEntity<WishlistDto>(this.wishlistService.addToWishlist(wishlistDto, customerId),
				HttpStatus.CREATED);
	}

	// update wish list by wish list id and customer id
	@PutMapping("/update/customerId/{customerId}/wishlistId/{wishlistId}")
	public ResponseEntity<WishlistDto> updateWishlistByWishlistIdAndCustomerId(
			@Valid @RequestBody WishlistDto wishlistDto, @PathVariable("customerId") Long customerId,
			@PathVariable("wishlistId") Long wishlistId) {
		return ResponseEntity
				.ok(this.wishlistService.updateWishlistByWishlistIdAndCustomerId(wishlistDto, customerId, wishlistId));
	}

	// delete single wish list by wish list id and customer id
	@DeleteMapping("/delete/deleteSingleWishlistByWishlistIdAndCustomerId/customerId/{customerId}/wishlistId/{wishlistId}")
	public ResponseEntity<ApiResponse> deleteSingleWishlistByWishlistIdAndCustomerId(
			@PathVariable("customerId") Long customerId, @PathVariable("wishlistId") Long wishlistId) {
		this.wishlistService.deleteSingleWishlistByWishlistIdAndCustomerId(customerId, wishlistId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Wishlist deleted successfully", true), HttpStatus.OK);
	}

	// delete list of wish lists by customer id
	@DeleteMapping("/delete/deleteListOfWishlistsByCustomerId/{customerId}")
	public ResponseEntity<ApiResponse> deleteListOfWishlistsByCustomerId(@PathVariable("customerId") Long customerId) {
		this.wishlistService.deleteListOfWishlistsByCustomerId(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("All wishlists deleted successfully", true),
				HttpStatus.OK);
	}

	// get all wish lists
	@GetMapping("/getAllWishlists")
	public ResponseEntity<List<WishlistDto>> getAllWishlists() {
		return ResponseEntity.ok(this.wishlistService.getAllWishlists());
	}

	// get single wish list by customer id and wish list id
	@GetMapping("/getSingleWishlistByWishlistIdAndCustomerId/customerId/{customerId}/wishlistId/{wishlistId}")
	public ResponseEntity<WishlistDto> getSingleWishlistByWishlistIdAndCustomerId(
			@PathVariable("customerId") Long customerId, @PathVariable("wishlistId") Long wishlistId) {
		return ResponseEntity
				.ok(this.wishlistService.getSingleWishlistByWishlistIdAndCustomerId(customerId, wishlistId));
	}

	// get list of wish lists by customer id
	@GetMapping("/getListOfWishlistsByCustomerId/{customerId}")
	public ResponseEntity<List<WishlistDto>> getListOfWishlistsByCustomerId(
			@PathVariable("customerId") Long customerId) {
		List<WishlistDto> wishlistListByCustomerId = this.wishlistService.getListOfWishlistsByCustomerId(customerId);
		return new ResponseEntity<List<WishlistDto>>(wishlistListByCustomerId, HttpStatus.OK);
	}

}
