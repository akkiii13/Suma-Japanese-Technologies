package sjtxev.customers_carts_wishlists.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class WishlistDto {

	private WishlistPrimaryKeyDto wishlistId;

	@NotNull(message = "Product id must not be null")
	private Long productId;

}
