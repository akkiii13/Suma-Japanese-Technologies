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
public class ProductImagePrimaryKey {

	@Column(name = "productImageIdPK")
	private Long productImageIdPK;

	@Column(name = "productIdPK")
	private Long productIdPK;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductImagePrimaryKey productImagePrimaryKey = (ProductImagePrimaryKey) o;
		return Objects.equals(productImageIdPK, productImagePrimaryKey.productImageIdPK)
				&& Objects.equals(productIdPK, productImagePrimaryKey.productIdPK);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productImageIdPK, productIdPK);
	}

}
