package com.sjtev.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjtev.entities.helper.VehicleIdPrimaryKey;
import com.sjtev.enums.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@EmbeddedId
	@Column(nullable = false)
	private VehicleIdPrimaryKey vehicleId;

	@Column(length = 50)
	private String brand;

	@Column(length = 50)
	private String model;

	@Column(length = 50)
	private String modelYear;

	@Column(length = 50)
	private String transmission;

	@Column(length = 50)
	private String fuel;

	@Column(length = 50)
	private String carColour;

	@Column(length = 50)
	private String registrationNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate registrationDate;

	@Column(length = 50)
	private String chassisNumber;

	@Column(length = 50)
	private String motorNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate expectedConversionDate;

	@Column(length = 50)
	private String rtoLocation;

	@Column(length = 50)
	private String cityOfCarLocation;

	@Column(length = 50)
	private String pincodeOfCarLocation;

	@Lob
	@Column
	private String anyOtherInformationAboutVehicle;

	@Enumerated(EnumType.STRING)
	private Category category;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "UTC+5:30")
	@Temporal(TemporalType.TIME)
	private LocalTime createdTime;

	@ManyToOne
	@MapsId("inquiryIdPK")
	@JoinColumn(name = "inquiryId")
	@JsonIgnore
	private Inquiry inquiry;

//	@OneToMany(mappedBy = "vehicles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Servicing> servicings;

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Vehicle vehicles))
			return false;
		if (!getVehicleId().equals(vehicles.getVehicleId()))
			return false;
		if (!getBrand().equals(vehicles.getBrand()))
			return false;
		if (!getModel().equals(vehicles.getModel()))
			return false;
		if (!getModelYear().equals(vehicles.getModelYear()))
			return false;
		if (!getTransmission().equals(vehicles.getTransmission()))
			return false;
		if (!getFuel().equals(vehicles.getFuel()))
			return false;
		if (!getCarColour().equals(vehicles.getCarColour()))
			return false;
		if (!getRegistrationNumber().equals(vehicles.getRegistrationNumber()))
			return false;
		if (!getRegistrationDate().equals(vehicles.getRegistrationDate()))
			return false;
		if (!getChassisNumber().equals(vehicles.getChassisNumber()))
			return false;
		if (!getMotorNumber().equals(vehicles.getMotorNumber()))
			return false;
		if (!getExpectedConversionDate().equals(vehicles.getExpectedConversionDate()))
			return false;
		if (!getRtoLocation().equals((vehicles.getRtoLocation())))
			return false;
		if (!getCityOfCarLocation().equals(vehicles.getCityOfCarLocation()))
			return false;
		if (!getPincodeOfCarLocation().equals(vehicles.getPincodeOfCarLocation()))
			return false;
		if (!getAnyOtherInformationAboutVehicle().equals(vehicles.getAnyOtherInformationAboutVehicle()))
			return false;
		if (!getCreatedDate().equals(vehicles.getCreatedDate()))
			return false;
		if (!getCreatedTime().equals(vehicles.getCreatedTime()))
			return false;
		if (!getCategory().equals(vehicles.getCategory()))
			return false;
		return true;
	}

	@Override
	public final int hashCode() {
		int result = getVehicleId().hashCode();
		if (getBrand() != null)
			result = 31 * result + getBrand().hashCode();
		if (getModel() != null)
			result = 31 * result + getModel().hashCode();
		if (getModelYear() != null)
			result = 31 * result + getModelYear().hashCode();
		if (getTransmission() != null)
			result = 31 * result + getTransmission().hashCode();
		if (getFuel() != null)
			result = 31 * result + getFuel().hashCode();
		if (getCarColour() != null)
			result = 31 * result + getCarColour().hashCode();
		if (getRegistrationNumber() != null)
			result = 31 * result + getRegistrationNumber().hashCode();
		if (getRegistrationDate() != null)
			result = 31 * result + getRegistrationDate().hashCode();
		if (getChassisNumber() != null)
			result = 31 * result + getChassisNumber().hashCode();
		if (getMotorNumber() != null)
			result = 31 * result + getMotorNumber().hashCode();
		if (getExpectedConversionDate() != null)
			result = 31 * result + getExpectedConversionDate().hashCode();
		if (getRtoLocation() != null)
			result = 31 * result + getRtoLocation().hashCode();
		if (getCityOfCarLocation() != null)
			result = 31 * result + getCityOfCarLocation().hashCode();
		if (getPincodeOfCarLocation() != null)
			result = 31 * result + getPincodeOfCarLocation().hashCode();
		if (getAnyOtherInformationAboutVehicle() != null)
			result = 31 * result + getAnyOtherInformationAboutVehicle().hashCode();
		if (getCreatedDate() != null)
			result = 31 * result + getCreatedDate().hashCode();
		if (getCreatedTime() != null)
			result = 31 * result + getCreatedTime().hashCode();
		if (getCategory() != null)
			result = 31 * result + getCategory().hashCode();
		return result;
	}

}
