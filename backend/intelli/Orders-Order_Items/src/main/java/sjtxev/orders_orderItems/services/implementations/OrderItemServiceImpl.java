package sjtxev.orders_orderItems.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sjtxev.orders_orderItems.entities.Order;
import sjtxev.orders_orderItems.entities.OrderItem;
import sjtxev.orders_orderItems.entities.Product;
import sjtxev.orders_orderItems.entities.entityHelper.OrderItemPrimaryKey;
import sjtxev.orders_orderItems.exceptions.ResourceNotFoundException;
import sjtxev.orders_orderItems.payloads.OrderItemDto;
import sjtxev.orders_orderItems.repositories.OrderItemRepository;
import sjtxev.orders_orderItems.repositories.OrderRepository;
import sjtxev.orders_orderItems.services.OrderItemService;
import sjtxev.orders_orderItems.services.ProductsClient;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	OrderRepository ordersRepository;

	@Autowired
	ProductsClient productsClient;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// save orderItem
	@Override
	public OrderItemDto saveOrderItem(@Valid OrderItemDto orderItemDto) {

		Long productId = orderItemDto.getProductId();
		Long orderId = orderItemDto.getOrder().getOrderId();

		if (productId == null)
			throw new IllegalArgumentException("Product id must not be null");

		Product product = null;

		ResponseEntity<Product> response = this.productsClient.findProductByProductId(productId);

		if (response.getStatusCode() == HttpStatus.OK)
			product = response.getBody();
		else
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		if (orderId == null)
			throw new IllegalArgumentException("Order id must not be null");

		Order order = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "order id", String.valueOf(orderId)));

		Integer quantity = product.getQuantity();

		if (orderItemDto.getQuantity() > quantity)
			throw new IllegalArgumentException("Only " + quantity + " this much is available for now.");

		Long maxOrderItemId = orderItemRepository.findMaxOrderItemId();
		Long nextOrderItemId = (maxOrderItemId != null) ? maxOrderItemId + 1 : 1;

		OrderItemPrimaryKey orderItemPrimaryKey = new OrderItemPrimaryKey();
		orderItemPrimaryKey.setOrderItemIdPK(orderId);
		orderItemPrimaryKey.setOrderItemIdPK(nextOrderItemId);

		OrderItem orderItem = this.mapToOrderItem(orderItemDto);
		orderItem.setOrder(order);
		orderItem.setProductId(productId);
		orderItem.setOrderItemId(orderItemPrimaryKey);

		OrderItem savedOrderItem = this.orderItemRepository.save(orderItem);

		product.setQuantity(quantity - orderItem.getQuantity());

		this.productsClient.updateProductByProductId(product, productId);

		return this.mapToOrderItemDto(savedOrderItem);

	}

	// update orderItem by orderItem id
	@Override
	public OrderItemDto updateOrderItemByOrderItemId(@Valid OrderItemDto orderItemDto, Long orderItemId) {

		Long productId = orderItemDto.getProductId();
		Long orderId = orderItemDto.getOrder().getOrderId();

		if (orderItemId == null)
			throw new IllegalArgumentException("Order item id must not be null");

		if (orderId == null)
			throw new IllegalArgumentException("Order id must not be null");

		Order order = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "order id", Long.toString(orderId)));

		OrderItemPrimaryKey orderItemPrimaryKey = new OrderItemPrimaryKey();
		orderItemPrimaryKey.setOrderIdPK(orderId);
		orderItemPrimaryKey.setOrderItemIdPK(orderItemId);

		OrderItem orderItem = this.orderItemRepository.findById(orderItemPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("OrderItem", "orderItem id",
						String.valueOf(orderItemId) + " and order id : " + String.valueOf(orderId)));

		if (productId == null)
			throw new IllegalArgumentException("Product id must not be null");

		Product product = null;

		ResponseEntity<Product> response = this.productsClient.findProductByProductId(productId);

		if (response.getStatusCode() == HttpStatus.OK)
			product = response.getBody();
		else
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		Integer quantity = product.getQuantity();

		if (orderItemDto.getQuantity() > quantity)
			throw new IllegalArgumentException("Only " + quantity + " this much is available for now.");

		product.setQuantity(product.getQuantity() + quantity - orderItem.getQuantity());

		orderItem.setOrderItemId(orderItemPrimaryKey);
		orderItem.setPrice(orderItemDto.getPrice());
		orderItem.setProductId(productId);
		orderItem.setOrder(order);
		orderItem.setQuantity(orderItem.getQuantity());

		this.productsClient.updateProductByProductId(product, productId);

		OrderItem savedOrderItem = this.orderItemRepository.save(orderItem);

		return this.mapToOrderItemDto(savedOrderItem);

	}

	// delete single orderItem by orderItem id
	@Override
	public void deleteSingleOrderItemByOrderItemIdAndOrderId(Long orderItemId, Long orderId) {

		if (orderItemId == null)
			throw new IllegalArgumentException("OrderItem id must not be null");

		if (orderId == null)
			throw new IllegalArgumentException("Order id must not be null");

		OrderItemPrimaryKey orderItemPrimaryKey = new OrderItemPrimaryKey();
		orderItemPrimaryKey.setOrderIdPK(orderId);
		orderItemPrimaryKey.setOrderItemIdPK(orderItemId);

		OrderItem orderItem = this.orderItemRepository.findById(orderItemPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("OrderItem", "orderItem id",
						String.valueOf(orderItemId) + " and order id : " + String.valueOf(orderId)));

		this.orderItemRepository.delete(orderItem);

	}

	// delete list of orderItems by order id
	@Override
	public void deleteListOfOrderItemsByOrderId(Long orderId) {

		if (orderId == null)
			throw new IllegalArgumentException("Order id must not be null");

		Order order = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "order id", String.valueOf(orderId)));

		List<OrderItem> findByOrderId = this.orderItemRepository.findByOrder(order);

		if (findByOrderId.isEmpty())
			throw new ResourceNotFoundException("OrderItem", "order id", Long.toString(orderId));

		findByOrderId.stream().forEach(i -> this.orderItemRepository.delete(i));

	}

	// get all orderItems
	@Override
	public List<OrderItemDto> getAllOrderItems() {

		List<OrderItem> findAll = this.orderItemRepository.findAll();

		if (findAll.isEmpty())
			throw new ResourceNotFoundException("OrderItem", null, null);

		return findAll.stream().map(this::mapToOrderItemDto).collect(Collectors.toList());

	}

	// get list of orderItems by order id
	@Override
	public List<OrderItemDto> getListOfOrderItemsByOrderId(Long orderId) {

		if (orderId == null)
			throw new IllegalArgumentException("Order id cannot be null");

		Order order = this.ordersRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Orders", "order id", String.valueOf(orderId)));

		List<OrderItem> findByOrderId = this.orderItemRepository.findByOrder(order);

		if (findByOrderId.isEmpty())
			throw new ResourceNotFoundException("OrderItem", "order id", Long.toString(orderId));

		return findByOrderId.stream().map(this::mapToOrderItemDto).collect(Collectors.toList());

	}

	// get single orderItem by orderItem id and order id
	@Override
	public OrderItemDto getSingleOrderItemByOrderItemIdAndOrderId(Long orderItemId, Long orderId) {

		if (orderItemId == null)
			throw new IllegalArgumentException("OrderItem id cannot be null");

		if (orderId == null)
			throw new IllegalArgumentException("Order id cannot be null");

		OrderItemPrimaryKey orderItemPrimaryKey = new OrderItemPrimaryKey();
		orderItemPrimaryKey.setOrderIdPK(orderId);
		orderItemPrimaryKey.setOrderItemIdPK(orderItemId);

		OrderItem orderItem = this.orderItemRepository.findById(orderItemPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("OrderItem", "orderItem id",
						String.valueOf(orderItemId) + " and order id : " + String.valueOf(orderId)));

		return this.mapToOrderItemDto(orderItem);

	}

	private OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
		return this.modelMapper.map(orderItem, OrderItemDto.class);
	}

	private OrderItem mapToOrderItem(OrderItemDto orderItemDto) {
		return this.modelMapper.map(orderItemDto, OrderItem.class);
	}

}
