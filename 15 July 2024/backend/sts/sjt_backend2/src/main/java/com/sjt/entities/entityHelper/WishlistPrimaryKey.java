package com.sjt.entities.entityHelper;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WishlistPrimaryKey wishlistPrimaryKey = (WishlistPrimaryKey) o;
		return Objects.equals(wishlistIdPK, wishlistPrimaryKey.wishlistIdPK)
				&& Objects.equals(customerIdPK, wishlistPrimaryKey.customerIdPK);
	}

	@Override
	public int hashCode() {
		return Objects.hash(wishlistIdPK, customerIdPK);
	}

}
