package sjtxev.products.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column
	private String brandName;

	@Column
	private String modelName;

	@Column
	private Double price;

	@Column
	private String description;

	@Column
	private Integer quantity;

	@Column
	private Boolean availability;

	@Column
	private Long categoryId;

	@Column
	transient private List<Long> productImage;

//	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JsonIgnore
//	private List<Cart> cart;
//
//	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JsonIgnore
//	private List<Wishlist> wishlist;
//
//	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JsonIgnore
//	private List<OrderItem> orderItem;
//
//	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JsonIgnore
//	private List<ProductImage> productImage;

}
