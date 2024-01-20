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
public class OrderItemPrimaryKey {

	@Column(name = "orderItemIdPK")
	private Long orderItemIdPK;

	@Column(name = "orderIdPK")
	private Long orderIdPK;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderItemPrimaryKey orderItemPrimaryKey = (OrderItemPrimaryKey) o;
		return Objects.equals(orderItemIdPK, orderItemPrimaryKey.orderItemIdPK)
				&& Objects.equals(orderIdPK, orderItemPrimaryKey.orderIdPK);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderItemIdPK, orderIdPK);
	}

}
