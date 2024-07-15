package sjtxev.customers_carts_wishlists.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sjtxev.customers_carts_wishlists.entities.entityHelper.WishlistPrimaryKey;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "wishlist")
public class Wishlist {

	@EmbeddedId
	private WishlistPrimaryKey wishlistId;

	private Long productId;

}
