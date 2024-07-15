package com.sjtev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class VehicleSearchCriteria {
	private String brand;
	private String model;
	private String modelYear;
	private String transmission;
	private String fuel;
	private String carColour;
	private String registrationNumber;
	private String chassisNumber;
	private String motorNumber;
	private String rtoLocation;
	private String cityOfCarLocation;
	private String pincodeOfCarLocation;
	private String anyOtherInformationAboutVehicle;
}
