package com.sjt.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjt.entities.Customers;
import com.sjt.entities.Products;
import com.sjt.entities.Wishlist;
import com.sjt.entities.entityHelper.WishlistPrimaryKey;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.WishlistDto;
import com.sjt.repositories.CustomersRepository;
import com.sjt.repositories.ProductsRepository;
import com.sjt.repositories.WishlistRepository;
import com.sjt.services.WishlistService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	WishlistRepository wishlistRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	ProductsRepository productsRepository;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// add to wish list
	@Override
	public WishlistDto addToWishlist(@Valid WishlistDto wishlistDto, Long customerId) {

		Long productId = wishlistDto.getProducts().getProductId();

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		if (productId == null)
			throw new IllegalArgumentException("Product ID must be provided.");

		Products product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product id", Long.toString(productId)));

		Long maxWishlistId = wishlistRepository.findMaxWishlistIdByCustomerId(customerId);
		Long nextWishlistId = (maxWishlistId != null) ? maxWishlistId + 1 : 1;

		WishlistPrimaryKey wishlistPrimaryKey = new WishlistPrimaryKey();
		wishlistPrimaryKey.setWishlistIdPK(nextWishlistId);
		wishlistPrimaryKey.setCustomerIdPK(customerId);

		Wishlist wishlist = this.mapToWishlist(wishlistDto);

		wishlist.setWishlistId(wishlistPrimaryKey);
		wishlist.setCustomers(customer);
		wishlist.setProducts(product);

		entityManager.persist(wishlist);

		Wishlist savedwishlist = this.wishlistRepository.save(wishlist);

		return this.mapToWishlistDto(savedwishlist);

	}

	// update wish list by wish list id and customer id
	@Override
	public WishlistDto updateWishlistByWishlistIdAndCustomerId(@Valid WishlistDto wishlistDto, Long customerId,
			Long wishlistId) {

		Long productId = wishlistDto.getProducts().getProductId();

		if (wishlistId == null)
			throw new IllegalArgumentException("Wish list id must be provided.");

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		if (productId == null)
			throw new IllegalArgumentException("Product ID must be provided.");

		Products product = this.productsRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product id", Long.toString(productId)));

		WishlistPrimaryKey wishlistPrimaryKey = new WishlistPrimaryKey();
		wishlistPrimaryKey.setCustomerIdPK(customerId);
		wishlistPrimaryKey.setWishlistIdPK(wishlistId);

		Wishlist wishlist = this.wishlistRepository.findById(wishlistPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Wish list", "wish list id",
						String.valueOf(wishlistId) + " and customer id : " + String.valueOf(customerId)));

		wishlist.setCustomers(customer);
		wishlist.setWishlistId(wishlistPrimaryKey);
		wishlist.setProducts(product);

		Wishlist updatedWishlist = this.wishlistRepository.save(wishlist);

		return this.mapToWishlistDto(updatedWishlist);

	}

	// delete single wish list by wish list id and customer id
	@Override
	public void deleteSingleWishlistByWishlistIdAndCustomerId(Long customerId, Long wishlistId) {

		if (wishlistId == null)
			throw new IllegalArgumentException("Wish list id must be provided.");

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		WishlistPrimaryKey wishlistPrimaryKey = new WishlistPrimaryKey();
		wishlistPrimaryKey.setCustomerIdPK(customerId);
		wishlistPrimaryKey.setWishlistIdPK(wishlistId);

		Wishlist wishlist = this.wishlistRepository.findById(wishlistPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Wish list", "wish list id",
						String.valueOf(wishlistId) + " and customer id : " + String.valueOf(customerId)));

		this.wishlistRepository.delete(wishlist);

	}

	// delete list of wish lists by customer id
	@Override
	public void deleteListOfWishlistsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customers = this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		List<Wishlist> findByCustomerId = this.wishlistRepository.findWishlistByCustomers(customers);

		if (findByCustomerId.isEmpty())
			throw new ResourceNotFoundException("Wish list", "customer id", String.valueOf(customerId));

		findByCustomerId.stream().forEach(wishlist -> this.wishlistRepository.delete(wishlist));

	}

	// get all wish lists
	@Override
	public List<WishlistDto> getAllWishlists() {

		List<Wishlist> wishlists = this.wishlistRepository.findAll();

		if (wishlists.isEmpty())
			throw new ResourceNotFoundException("Wish lists", "", "");

		return wishlists.stream().map(this::mapToWishlistDto).collect(Collectors.toList());

	}

	// get single wish list by customer id and wish list id
	@Override
	public WishlistDto getSingleWishlistByWishlistIdAndCustomerId(Long customerId, Long wishlistId) {

		if (wishlistId == null)
			throw new IllegalArgumentException("Wish list id must be provided.");

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		this.customersRepository.findByCustomerIdAndIsActiveTrue(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId)));

		WishlistPrimaryKey wishlistPrimaryKey = new WishlistPrimaryKey();
		wishlistPrimaryKey.setCustomerIdPK(customerId);
		wishlistPrimaryKey.setWishlistIdPK(wishlistId);

		Wishlist wishlist = this.wishlistRepository.findById(wishlistPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Wish list", "wish list id",
						String.valueOf(wishlistId) + " and customer id : " + String.valueOf(customerId)));

		return this.mapToWishlistDto(wishlist);

	}

	// get list of wish lists by customer id
	@Override
	public List<WishlistDto> getListOfWishlistsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Customers customer = this.customersRepository.findById(customerId).filter(Customers::getIsActive)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customer id", customerId.toString()));

		List<Wishlist> findByCustomer = this.wishlistRepository.findWishlistByCustomers(customer);

		if (findByCustomer.isEmpty())
			throw new ResourceNotFoundException("Wish list", "", "");

		return findByCustomer.stream().map(this::mapToWishlistDto).collect(Collectors.toList());

	}

	private WishlistDto mapToWishlistDto(Wishlist wishlist) {
		return this.modelMapper.map(wishlist, WishlistDto.class);
	}

	private Wishlist mapToWishlist(WishlistDto wishlistDto) {
		return this.modelMapper.map(wishlistDto, Wishlist.class);
	}

}
