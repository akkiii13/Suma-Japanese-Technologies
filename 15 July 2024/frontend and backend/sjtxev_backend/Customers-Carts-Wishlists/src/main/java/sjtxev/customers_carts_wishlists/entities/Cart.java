package sjtxev.customers_carts_wishlists.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sjtxev.customers_carts_wishlists.entities.entityHelper.CartPrimaryKey;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cart")
public class Cart {

	@EmbeddedId
	private CartPrimaryKey cartId;

	@Column
	private LocalDate modifiedDate;

	@Column
	private LocalTime modifiedTime;

	@Column
	private Integer quantity;

	private Long productId;
	
}
