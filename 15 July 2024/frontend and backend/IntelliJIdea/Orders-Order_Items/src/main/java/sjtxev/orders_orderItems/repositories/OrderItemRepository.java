package sjtxev.orders_orderItems.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sjtxev.orders_orderItems.entities.OrderItem;
import sjtxev.orders_orderItems.entities.Order;
import sjtxev.orders_orderItems.entities.entityHelper.OrderItemPrimaryKey;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPrimaryKey> {

	// find list of order items by order
	List<OrderItem> findByOrder(Order orderId);

	// find max order item by order item id
	@Query(value = "SELECT MAX(orderItemIdPk) FROM orderitem", nativeQuery = true)
	Long findMaxOrderItemId();

}
