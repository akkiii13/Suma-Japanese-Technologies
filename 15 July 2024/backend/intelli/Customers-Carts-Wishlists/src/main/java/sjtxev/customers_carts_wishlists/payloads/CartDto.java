package sjtxev.customers_carts_wishlists.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sjtxev.customers_carts_wishlists.entities.entityHelper.CartPrimaryKey;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CartDto {

	private CartPrimaryKey cartId;

	private LocalDate modifiedDate;

	private LocalTime modifiedTime;

	@NotNull(message = "Quantity must not be blank")
	private Integer quantity;

	@NotNull(message = "Product id must not be null")
	private Long productId;

}
