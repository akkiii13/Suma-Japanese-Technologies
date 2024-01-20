package sjtxev.customers_carts_wishlists.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Product {

	private Long productId;

	private String brandName;

	private String modelName;

	private Double price;

	private String description;

	private Integer quantity;

	private Boolean availability;

	private Long categoryId;

	transient private List<Long> productImage;

	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	private List<Cart> cart;

}
