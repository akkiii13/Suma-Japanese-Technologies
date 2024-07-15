package sjtxev.customers_carts_wishlists.services;

import java.util.List;

import jakarta.validation.Valid;
import sjtxev.customers_carts_wishlists.payloads.WishlistDto;

public interface WishlistService {

	// add to wish list
	WishlistDto addToWishlist(@Valid WishlistDto wishlistDto, Long customerId);

	// update wish list by wish list id and customer id
	WishlistDto updateWishlistByWishlistIdAndCustomerId(@Valid WishlistDto wishlistDto, Long customerId,
			Long wishlistId);

	// delete single wish list by wish list id and customer id
	void deleteSingleWishlistByWishlistIdAndCustomerId(Long customerId, Long wishlistId);

	// delete list of wish lists by customer id
	void deleteListOfWishlistsByCustomerId(Long customerId);

	// get all wish lists
	List<WishlistDto> getAllWishlists();

	// get single wish list by customer id and wish list id
	WishlistDto getSingleWishlistByWishlistIdAndCustomerId(Long customerId, Long wishlistId);

	// get list of wish lists by customer id
	List<WishlistDto> getListOfWishlistsByCustomerId(Long customerId);

}
