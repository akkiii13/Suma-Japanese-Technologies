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
public class CartPrimaryKey {

	@Column(name = "cartIdPK")
	private Long cartIdPK;

	@Column(name = "customerIdPK")
	private Long customerIdPK;

}
