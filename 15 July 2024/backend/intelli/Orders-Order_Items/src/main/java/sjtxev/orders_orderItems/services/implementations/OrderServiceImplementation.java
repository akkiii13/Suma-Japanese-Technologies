package sjtxev.orders_orderItems.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sjtxev.orders_orderItems.entities.Order;
import sjtxev.orders_orderItems.exceptions.ResourceNotFoundException;
import sjtxev.orders_orderItems.payloads.OrderDto;
import sjtxev.orders_orderItems.repositories.OrderItemRepository;
import sjtxev.orders_orderItems.repositories.OrderRepository;
import sjtxev.orders_orderItems.services.CustomersClient;
import sjtxev.orders_orderItems.services.OrderService;
import sjtxev.orders_orderItems.services.PaymentsClient;
import sjtxev.orders_orderItems.services.ShipmentsClient;

@Service
@Transactional
public class OrderServiceImplementation implements OrderService {

	@Autowired
	OrderRepository ordersRepository;

	@Autowired
	CustomersClient customersClient;

	@Autowired
	PaymentsClient paymentsClient;

	@Autowired
	ShipmentsClient shipmentsClient;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// place order
	@Override
	public OrderDto placeOrder(@Valid OrderDto orderDto, Long customerId) {

		Long paymentId = orderDto.getPaymentId();
		Long shipmentId = orderDto.getShipmentId();

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Boolean isCustomerExists = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!isCustomerExists)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		if (paymentId == null)
			throw new IllegalArgumentException("Payment id must be provided.");

		Boolean isPaymentExists = this.paymentsClient.existsSinglePaymentByPaymentIdAndCustomerId(paymentId,
				customerId);

		if (!isPaymentExists)
			throw new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId));

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id must be provided.");

		Boolean isShipmentExists = this.shipmentsClient.existsSingleShipmentByShipmentIdAndCustomerId(shipmentId,
				customerId);

		if (!isShipmentExists)
			throw new ResourceNotFoundException("Shipment", "shipment id", String.valueOf(shipmentId));

		Order orders = this.mapToOrder(orderDto);

		orders.setOrderTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		orders.setOrderDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		orders.setCustomerId(customerId);
		orders.setPaymentId(paymentId);
		orders.setShipmentId(shipmentId);

		Order Ordered = this.ordersRepository.save(orders);

		return this.mapToOrderDto(Ordered);

	}

	// update order by order id and customer id
	@Override
	public OrderDto updateOrderByOrderIdAndCustomerId(@Valid OrderDto orderDto, Long orderId, Long customerId) {

		Long paymentId = orderDto.getPaymentId();
		Long shipmentId = orderDto.getShipmentId();

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Boolean isCustomerExists = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!isCustomerExists)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		if (orderId == null)
			throw new IllegalArgumentException("Orders id must be provided.");

		this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "orders id", Long.toString(orderId)));

		if (paymentId == null)
			throw new IllegalArgumentException("Payment id must be provided.");

		Boolean isPaymentExists = this.paymentsClient.existsSinglePaymentByPaymentIdAndCustomerId(paymentId,
				customerId);

		if (!isPaymentExists)
			throw new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId));

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id must be provided.");

		Boolean isShipmentExists = this.shipmentsClient.existsSingleShipmentByShipmentIdAndCustomerId(shipmentId,
				customerId);

		if (!isShipmentExists)
			throw new ResourceNotFoundException("Shipment", "shipment id", String.valueOf(shipmentId));

		Order order = this.mapToOrder(orderDto);

		order.setOrderTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		order.setOrderDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		order.setCustomerId(customerId);
		order.setPaymentId(paymentId);
		order.setShipmentId(shipmentId);
		order.setTotalAmount(orderDto.getTotalAmount());

		Order Ordered = this.ordersRepository.save(order);

		return this.mapToOrderDto(Ordered);

	}

	// delete single order by order id and customer id
	@Override
	public void deleteSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId) {

		if (orderId == null)
			throw new IllegalArgumentException("Orders id must be provided.");

		Order order = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "orders id", Long.toString(orderId)));

		this.ordersRepository.delete(order);

	}

	// delete list of orders by customer id
	@Override
	public void deleteListOfOrdersByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Boolean isExists = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!isExists)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		List<Order> findByCustomers = this.ordersRepository.findByCustomerId(customerId);

		if (findByCustomers.isEmpty())
			throw new ResourceNotFoundException("Orders", "customer id", String.valueOf(customerId));

		findByCustomers.stream().forEach(order -> this.ordersRepository.delete(order));

	}

	// get all orders
	@Override
	public List<OrderDto> getAllOrders() {

		List<Order> findAll = this.ordersRepository.findAll();

		if (findAll.isEmpty())
			throw new ResourceNotFoundException("Orders", "", "");

		return findAll.stream().map(this::mapToOrderDto).collect(Collectors.toList());

	}

	// get single order by order id and customer id
	@Override
	public OrderDto getSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId) {

		if (orderId == null)
			throw new IllegalArgumentException("Order id must be provided");

		Order order = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "order id", String.valueOf(orderId)));

		return this.mapToOrderDto(order);

	}

	// get list of orders by customer id
	@Override
	public List<OrderDto> getListOfOrdersByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Boolean isExists = this.customersClient.existsByCustomerIdAndIsActiveTrue(customerId);

		if (!isExists)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		List<Order> findByCustomer = this.ordersRepository.findByCustomerId(customerId);

		return findByCustomer.stream().map(this::mapToOrderDto).collect(Collectors.toList());

	}

	private OrderDto mapToOrderDto(Order order) {
		return this.modelMapper.map(order, OrderDto.class);
	}

	private Order mapToOrder(OrderDto orderDto) {
		return this.modelMapper.map(orderDto, Order.class);
	}

}
