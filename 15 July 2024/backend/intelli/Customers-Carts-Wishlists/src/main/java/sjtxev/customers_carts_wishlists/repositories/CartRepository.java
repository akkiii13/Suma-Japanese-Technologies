package sjtxev.customers_carts_wishlists.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sjtxev.customers_carts_wishlists.entities.Cart;
import sjtxev.customers_carts_wishlists.entities.entityHelper.CartPrimaryKey;

public interface CartRepository extends JpaRepository<Cart, CartPrimaryKey> {

	// find list of carts by customer
	@Query(nativeQuery = true, value = "SELECT * FROM Cart c WHERE c.customerIdPK = :customerId")
	List<Cart> findCartByCustomerId(Long customerId);

	// find single cart by cart id
	Cart findByCartId(CartPrimaryKey cartPrimaryKey);

	// find max cart id by customer id
	@Query("SELECT MAX(c.cartId.cartIdPK) FROM Cart c WHERE c.cartId.customerIdPK = :customerIdPK")
	Long findMaxCartIdByCustomerId(Long customerIdPK);

	// check whether the product is exists or not by product id
	Boolean existsByProductId(Long productId);

}
