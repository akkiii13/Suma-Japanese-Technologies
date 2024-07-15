package com.sjtev.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sjtev.entities.Inquiry;
import com.sjtev.entities.helper.VehicleIdPrimaryKey;
import com.sjtev.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class VehicleDto {

	private VehicleIdPrimaryKey vehicleId;

	@NotBlank(message = "Please enter brand name.")
	private String brand;

	@NotBlank(message = "Please enter model name.")
	private String model;

	@NotBlank(message = "Please enter model year.")
	private String modelYear;

	@NotNull(message = "Please select transmission type.")
	private String transmission;

	private String fuel;

	private String carColour;

	private String registrationNumber;

	private LocalDate registrationDate;

	private String chassisNumber;

	private String motorNumber;

	private LocalDate expectedConversionDate;

	@NotBlank(message = "Please enter RTO location.")
	private String rtoLocation;

	@NotBlank(message = "Please enter curent car location.")
	private String cityOfCarLocation;

	private String pincodeOfCarLocation;

	private String anyOtherInformationAboutVehicle;

	@NotNull
	private Category category;

	private LocalDate createdDate;

	private LocalTime createdTime;

	@NotNull(message = "Inquiry id can not be null")
	private Inquiry inquiry;

}
