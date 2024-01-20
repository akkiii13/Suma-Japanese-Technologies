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
public class CartPrimaryKey {

	@Column(name = "cartIdPK")
	private Long cartIdPK;

	@Column(name = "customerIdPK")
	private Long customerIdPK;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CartPrimaryKey cartPrimaryKey = (CartPrimaryKey) o;
		return Objects.equals(cartIdPK, cartPrimaryKey.cartIdPK)
				&& Objects.equals(customerIdPK, cartPrimaryKey.customerIdPK);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartIdPK, customerIdPK);
	}

}
