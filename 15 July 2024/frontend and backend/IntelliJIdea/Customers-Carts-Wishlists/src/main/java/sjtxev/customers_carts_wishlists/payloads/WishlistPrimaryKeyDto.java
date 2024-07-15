package sjtxev.customers_carts_wishlists.payloads;

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

public class WishlistPrimaryKeyDto {

	private Long wishlistIdPK;

	private Long customerIdPK;

}
