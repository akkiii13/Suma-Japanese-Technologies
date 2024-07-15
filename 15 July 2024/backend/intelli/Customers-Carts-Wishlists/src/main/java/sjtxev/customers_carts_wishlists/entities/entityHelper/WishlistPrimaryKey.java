package sjtxev.customers_carts_wishlists.entities.entityHelper;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Embeddable
public class WishlistPrimaryKey {

	@Column(name = "wishlistIdPK")
	private Long wishlistIdPK;

	@Column(name = "customerIdPK")
	private Long customerIdPK;

}
