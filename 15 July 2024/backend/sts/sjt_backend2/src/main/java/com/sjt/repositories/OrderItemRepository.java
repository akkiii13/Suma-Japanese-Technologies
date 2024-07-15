package com.sjt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sjt.entities.OrderItem;
import com.sjt.entities.Orders;
import com.sjt.entities.entityHelper.OrderItemPrimaryKey;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPrimaryKey> {

	// find list of order items by order
	List<OrderItem> findByOrders(Orders orderId);

	// find max order item by order item id
	@Query(value = "SELECT MAX(order_item_idpk) FROM order_item", nativeQuery = true)
	Long findMaxOrderItemId();

}
