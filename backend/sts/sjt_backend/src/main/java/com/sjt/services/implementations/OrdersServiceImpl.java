package com.sjt.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjt.entities.Customers;
import com.sjt.entities.Orders;
import com.sjt.entities.Payment;
import com.sjt.entities.Shipment;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.OrdersDto;
import com.sjt.repositories.CustomersRepository;
import com.sjt.repositories.OrdersRepository;
import com.sjt.repositories.PaymentRepository;
import com.sjt.repositories.ShipmentRepository;
import com.sjt.services.OrdersService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ShipmentRepository shipmentRepository;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// place order
	@Override
	public OrdersDto placeOrder(@Valid OrdersDto ordersDto, Long customerId) {

		Long paymentId = ordersDto.getPayment().getPaymentId();
		Long shipmentId = ordersDto.getShipment().getShipmentId();

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		if (paymentId == null)
			throw new IllegalArgumentException("Payment id must be provided.");

		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId)));

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id must be provided.");

		Shipment shipment = this.shipmentRepository.findById(shipmentId).orElseThrow(
				() -> new ResourceNotFoundException("Shipment", "shipment id", String.valueOf(shipmentId)));

		if (customerId != shipment.getCustomers().getCustomerId())
			throw new IllegalArgumentException("Shipment not found with customer id " + String.valueOf(customerId));

		if (customerId != payment.getCustomers().getCustomerId())
			throw new IllegalArgumentException("Payment not found with customer id " + String.valueOf(customerId));

		Orders orders = this.mapToOrders(ordersDto);

		orders.setOrderTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		orders.setOrderDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		orders.setCustomers(customers);
		orders.setPayment(payment);
		orders.setShipment(shipment);

		Orders Ordered = this.ordersRepository.save(orders);

		return this.mapToOrdersDto(Ordered);

	}

	// update order by order id and customer id
	@Override
	public OrdersDto updateOrderByOrderIdAndCustomerId(@Valid OrdersDto ordersDto, Long orderId, Long customerId) {

		Long paymentId = ordersDto.getPayment().getPaymentId();
		Long shipmentId = ordersDto.getShipment().getShipmentId();

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		if (orderId == null)
			throw new IllegalArgumentException("Orders id must be provided.");

		this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "orders id", Long.toString(orderId)));

		if (paymentId == null)
			throw new IllegalArgumentException("Payment id must be provided.");

		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "payment id", Long.toString(paymentId)));

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id must be provided.");

		Shipment shipment = this.shipmentRepository.findById(shipmentId).orElseThrow(
				() -> new ResourceNotFoundException("Shipment", "shipment id", String.valueOf(shipmentId)));

		if (customerId != shipment.getCustomers().getCustomerId())
			throw new IllegalArgumentException("Shipment not found with customer id " + String.valueOf(customerId));

		if (customerId != payment.getCustomers().getCustomerId())
			throw new IllegalArgumentException("Payment not found with customer id " + String.valueOf(customerId));

		Orders orders = this.mapToOrders(ordersDto);

		orders.setOrderTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		orders.setOrderDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		orders.setCustomers(customers);
		orders.setPayment(payment);
		orders.setShipment(shipment);
		orders.setTotalAmount(ordersDto.getTotalAmount());

		Orders Ordered = this.ordersRepository.save(orders);

		return this.mapToOrdersDto(Ordered);

	}

	// delete single order by order id and customer id
	@Override
	public void deleteSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId) {

		if (orderId == null)
			throw new IllegalArgumentException("Orders id must be provided.");

		Orders orders = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "orders id", Long.toString(orderId)));

		this.ordersRepository.delete(orders);

	}

	// delete list of orders by customer id
	@Override
	public void deleteListOfOrdersByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		List<Orders> findByCustomers = this.ordersRepository.findByCustomers(customer);

		if (findByCustomers.isEmpty())
			throw new ResourceNotFoundException("Orders", "customer id", String.valueOf(customerId));

		findByCustomers.stream().forEach(order -> this.ordersRepository.delete(order));

	}

	// get all orders
	@Override
	public List<OrdersDto> getAllOrders() {

		List<Orders> findAll = this.ordersRepository.findAll();

		if (findAll.isEmpty())
			throw new ResourceNotFoundException("Orders", "", "");

		return findAll.stream().map(this::mapToOrdersDto).collect(Collectors.toList());

	}

	// get single order by order id and customer id
	@Override
	public OrdersDto getSingleOrderByOrderIdAndCustomerId(Long orderId, Long customerId) {

		if (orderId == null)
			throw new IllegalArgumentException("Order id must be provided");

		Orders orders = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "order id", String.valueOf(orderId)));

		return this.mapToOrdersDto(orders);

	}

	// get list of orders by customer id
	@Override
	public List<OrdersDto> getListOfOrdersByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		List<Orders> findByCustomer = this.ordersRepository.findByCustomers(customers);

		return findByCustomer.stream().map(this::mapToOrdersDto).collect(Collectors.toList());

	}

	private OrdersDto mapToOrdersDto(Orders orders) {
		return this.modelMapper.map(orders, OrdersDto.class);
	}

	private Orders mapToOrders(OrdersDto ordersDto) {
		return this.modelMapper.map(ordersDto, Orders.class);
	}

}
