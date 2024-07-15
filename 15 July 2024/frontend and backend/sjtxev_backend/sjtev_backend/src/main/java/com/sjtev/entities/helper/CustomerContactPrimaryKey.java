package com.sjtev.entities.helper;

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
public class CustomerContactPrimaryKey {

	@Column(name = "customerContactIdPK")
	private Long customerContactIdPK;

	@Column(name = "inquiryIdPK")
	private Long inquiryIdPK;

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CustomerContactPrimaryKey customerContactPrimaryKey))
			return false;
		if (!getCustomerContactIdPK().equals(customerContactPrimaryKey.getCustomerContactIdPK()))
			return false;
		if (!getInquiryIdPK().equals(customerContactPrimaryKey.getInquiryIdPK()))
			return false;
		return true;
	}

	@Override
	public final int hashCode() {
		int result = getCustomerContactIdPK().hashCode();
		result = 31 * result + getInquiryIdPK().hashCode();
		return result;
	}

}
