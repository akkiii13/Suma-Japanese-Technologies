package sjtxev.orders_orderItems.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sjtxev.orders_orderItems.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	// find list of orders by customer
	List<Order> findByCustomerId(Long customers);

}
