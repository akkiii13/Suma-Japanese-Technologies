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
public class VehicleIdPrimaryKey {

	@Column(name = "vehicleIdPK")
	private Long vehicleIdPK;

	@Column(name = "inquiryIdPK")
	private Long inquiryIdPK;

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof VehicleIdPrimaryKey vehicleIdPrimaryKey))
			return false;
		if (!getVehicleIdPK().equals(vehicleIdPrimaryKey.getVehicleIdPK()))
			return false;
		if (!getInquiryIdPK().equals(vehicleIdPrimaryKey.getInquiryIdPK()))
			return false;
		return true;
	}

	@Override
	public final int hashCode() {
		int result = getVehicleIdPK().hashCode();
		result = 31 * result + getInquiryIdPK().hashCode();
		return result;
	}

}
