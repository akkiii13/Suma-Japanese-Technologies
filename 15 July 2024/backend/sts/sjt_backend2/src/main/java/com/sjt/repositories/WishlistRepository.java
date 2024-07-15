package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sjt.entities.Customers;
import com.sjt.entities.Wishlist;
import com.sjt.entities.entityHelper.WishlistPrimaryKey;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistPrimaryKey> {

	// find list of wish lists by customer
	List<Wishlist> findWishlistByCustomers(Customers customer);

	// find single by wish list by wish list id
	Wishlist findSingleWishlistByWishlistId(WishlistPrimaryKey cartId);

	// find max wish list id
	@Query(value = "SELECT MAX(wishlist_idpk) FROM wishlist", nativeQuery = true)
	Long findMaxWishlistId();

	// find max wish list id by customer id
	@Query("SELECT MAX(w.wishlistId.wishlistIdPK) FROM Wishlist w WHERE w.wishlistId.customerIdPK = :customerIdPK")
	Long findMaxWishlistIdByCustomerId(Long customerIdPK);

}
