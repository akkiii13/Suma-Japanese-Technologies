package sjtxev.customers_carts_wishlists.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sjtxev.customers_carts_wishlists.entities.Wishlist;
import sjtxev.customers_carts_wishlists.entities.entityHelper.WishlistPrimaryKey;

public interface WishlistRepository extends JpaRepository<Wishlist, WishlistPrimaryKey> {

	// find list of wish lists by customer
	@Query(nativeQuery = true, value = "SELECT * FROM Wishlist w WHERE w.customerIdPK = :customerId")
	List<Wishlist> findWishlistByCustomerId(Long customerId);

	// find max wish list id by customer id
	@Query("SELECT MAX(w.wishlistId.wishlistIdPK) FROM Wishlist w WHERE w.wishlistId.customerIdPK = :customerIdPK")
	Long findMaxWishlistIdByCustomerId(Long customerIdPK);

	// check whether the product is exists or not by product id
	Boolean existsByWishlistId(WishlistPrimaryKey wishlistId);

}
