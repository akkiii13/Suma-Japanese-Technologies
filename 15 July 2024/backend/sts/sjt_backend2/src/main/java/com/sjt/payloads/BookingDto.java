package com.sjt.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

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

public class BookingDto {

	private Long bookingId;

	private String fullName;

	private String mobileNumber;

	private String emailAddress;

	private String ownerAddress;

	private String ownerPincode;

	private String brand;

	private String model;

	private String modelYear;

	private String transmission;

	private String fuel;

	private String carColour;

	private String registrationNumber;

	private LocalDate registrationDate;

	private String chassisNumber;

	private String motorNumber;

	private LocalDate expectedConversionDate;

	private String cityOfCarLocation;

	private String pincodeOfCarLocation;

	private String anyOtherInformation;

	private LocalDate modifiedDate;

	private LocalTime modifiedTime;

	private Long customerId;

}
