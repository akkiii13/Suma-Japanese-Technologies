package sjtxev.customers_carts_wishlists.services.implementations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
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
import sjtxev.customers_carts_wishlists.entities.Cart;
import sjtxev.customers_carts_wishlists.entities.Customer;
import sjtxev.customers_carts_wishlists.entities.Product;
import sjtxev.customers_carts_wishlists.entities.entityHelper.CartPrimaryKey;
import sjtxev.customers_carts_wishlists.exceptions.ResourceNotFoundException;
import sjtxev.customers_carts_wishlists.payloads.CartDto;
import sjtxev.customers_carts_wishlists.repositories.CartRepository;
import sjtxev.customers_carts_wishlists.repositories.CustomerRepository;
import sjtxev.customers_carts_wishlists.services.CartService;
import sjtxev.customers_carts_wishlists.services.ProductsClient;

@Service
@Transactional
public class CartServiceImplementation implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProductsClient productsClient;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// add to cart
	@Override
	public CartDto addToCart(@Valid CartDto cartDto, Long customerId) {

		Long productId = cartDto.getProductId();
		Integer quantity = cartDto.getQuantity();

		if (customerId == null)
			throw new IllegalArgumentException("Customer ID must be provided.");

		Boolean existsById = this.customerRepository.existsByCustomerIdAndIsActive(customerId, true);

		if (!existsById)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		if (productId == null)
			throw new IllegalArgumentException("Product ID must be provided.");

		if (this.cartRepository.existsByProductId(productId))
			throw new IllegalArgumentException("This product is already exists in your cart. Please check you cart.");

		ResponseEntity<Product> product = this.productsClient.getSingleProductByProductId(productId);

		if (quantity < 1)
			throw new IllegalArgumentException("Quantity must be atleast 1.");

		if (product.getBody() == null && product.getStatusCode() != HttpStatus.OK)
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		Integer actualProductQuantity = product.getBody().getQuantity();

		if (quantity > actualProductQuantity)
			throw new IllegalArgumentException("Only " + actualProductQuantity + " is available for now.");

		Long maxCartId = cartRepository.findMaxCartIdByCustomerId(customerId);
		System.out.println(maxCartId);
		Long nextCartId = (maxCartId != null) ? maxCartId + 1 : 1;

		CartPrimaryKey cartPrimaryKey = new CartPrimaryKey();
		cartPrimaryKey.setCartIdPK(nextCartId);
		cartPrimaryKey.setCustomerIdPK(customerId);

		Cart cart = this.mapToCart(cartDto);

		cart.setCartId(cartPrimaryKey);
		cart.setModifiedDate(LocalDate.now());
		cart.setModifiedTime(LocalTime.now());
		cart.setQuantity(quantity);
		cart.setProductId(productId);

		entityManager.persist(cart);

		Cart savedCart = this.cartRepository.save(cart);

		return this.mapToCartDto(savedCart);

	}

	// update cart by cart id and customer id
	@Override
	public CartDto updateCartByCartIdAndCustomerId(@Valid CartDto cartDto, Long cartId, Long customerId) {

		Long productId = cartDto.getProductId();
		Integer quantity = cartDto.getQuantity();
		Long cartIdPK = cartDto.getCartId().getCartIdPK();

		if (cartIdPK == null)
			throw new IllegalArgumentException("Cart ID must be provided.");

		if (customerId == null)
			throw new IllegalArgumentException("Customer ID must be provided.");

		Boolean existsById = this.customerRepository.existsByCustomerIdAndIsActive(customerId, true);

		if (!existsById)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		if (productId == null)
			throw new IllegalArgumentException("Product ID must be provided.");

		ResponseEntity<Product> product = this.productsClient.getSingleProductByProductId(productId);

		if (product.getBody() == null && product.getStatusCode() != HttpStatus.OK)
			throw new ResourceNotFoundException("Product", "product id", String.valueOf(productId));

		CartPrimaryKey cartPrimaryKey = new CartPrimaryKey();
		cartPrimaryKey.setCustomerIdPK(customerId);
		cartPrimaryKey.setCartIdPK(cartIdPK);

		Cart cart = this.cartRepository.findById(cartPrimaryKey).orElseThrow(() -> new ResourceNotFoundException("Cart",
				"cart id", String.valueOf(cartIdPK) + " and customer id : " + String.valueOf(customerId)));

		if (quantity < 1)
			throw new IllegalArgumentException("Quantity must be atleast 1.");

		Integer actualProductQuantity = product.getBody().getQuantity();

		if (quantity > actualProductQuantity)
			throw new IllegalArgumentException("Only " + actualProductQuantity + " is available for now.");

		cart.setQuantity(quantity);
		cart.setModifiedDate(LocalDate.now());
		cart.setModifiedTime(LocalTime.now());
		cart.setCartId(cartPrimaryKey);
		cart.setProductId(productId);

		Cart updatedCart = this.cartRepository.save(cart);

		return this.mapToCartDto(updatedCart);

	}

	// delete single cart by cart id and customer id
	@Override
	public void deleteSingleCartByCartIdAndCustomerId(Long cartId, Long customerId) {

		if (cartId == null)
			throw new IllegalArgumentException("Cart ID must be provided.");

		if (customerId == null)
			throw new IllegalArgumentException("Customer ID must be provided.");

		this.customerRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		CartPrimaryKey cartPrimaryKey = new CartPrimaryKey();
		cartPrimaryKey.setCustomerIdPK(customerId);
		cartPrimaryKey.setCartIdPK(cartId);

		Cart cart = this.cartRepository.findById(cartPrimaryKey).orElseThrow(() -> new ResourceNotFoundException("Cart",
				"cart id", String.valueOf(cartId) + " and customer id : " + String.valueOf(customerId)));

		this.cartRepository.delete(cart);

	}

	// get all carts
	@Override
	public List<CartDto> getAllCarts() {

		List<Cart> carts = this.cartRepository.findAll();

		if (carts.isEmpty())
			throw new ResourceNotFoundException("Carts", "", "");

		return carts.stream().map(this::mapToCartDto).collect(Collectors.toList());

	}

	// get single cart by cart id and customer id
	@Override
	public CartDto getSingleCartByCartIdAndCustomerId(Long cartId, Long customerId) {

		if (cartId == null)
			throw new IllegalArgumentException("Cart ID must be provided.");

		if (customerId == null)
			throw new IllegalArgumentException("Customer ID must be provided.");

		this.customerRepository.findById(customerId).filter(Customer::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", customerId.toString()));

		CartPrimaryKey cartPrimaryKey = new CartPrimaryKey();
		cartPrimaryKey.setCustomerIdPK(customerId);
		cartPrimaryKey.setCartIdPK(cartId);

		Cart cart = this.cartRepository.findById(cartPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Carts", "customer id",
						String.valueOf(customerId) + " and cart id : " + Long.valueOf(cartId)));

		return this.mapToCartDto(cart);

	}

	// get list of carts by customer id
	@Override
	public List<CartDto> getListOfCartsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer ID must be provided.");

		List<Cart> findByCustomer = this.cartRepository.findCartByCustomerId(customerId);

		if (findByCustomer.isEmpty())
			throw new ResourceNotFoundException("Carts", "customer id", String.valueOf(customerId));

		return findByCustomer
				.stream().sorted(Comparator.comparing(Cart::getModifiedDate).reversed()
						.thenComparing(Cart::getModifiedTime).reversed())
				.map(this::mapToCartDto).collect(Collectors.toList());

	}

	private CartDto mapToCartDto(Cart cart) {
		return this.modelMapper.map(cart, CartDto.class);
	}

	private Cart mapToCart(CartDto cartDto) {
		return this.modelMapper.map(cartDto, Cart.class);
	}

}
