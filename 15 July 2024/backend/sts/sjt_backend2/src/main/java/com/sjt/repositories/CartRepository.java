package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sjt.entities.Cart;
import com.sjt.entities.Customers;
import com.sjt.entities.entityHelper.CartPrimaryKey;

public interface CartRepository extends JpaRepository<Cart, CartPrimaryKey> {

	// find list of carts by customer
	List<Cart> findByCustomers(Customers customer);

	// find single cart by cart id
	Cart findByCartId(CartPrimaryKey cartPrimaryKey);

	// find max cart id
	@Query(value = "SELECT MAX(cart_idpk) FROM cart", nativeQuery = true)
	Long findMaxCartId();

	// find max cart id by customer id
	@Query("SELECT MAX(c.cartId.cartIdPK) FROM Cart c WHERE c.cartId.customerIdPK = :customerIdPK")
	Long findMaxCartIdByCustomerId(Long customerIdPK);

}
