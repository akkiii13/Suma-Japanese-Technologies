package com.sjt.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sjt.entities.Customers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

public class ShipmentDto {

	private Long shipmentId;

	private LocalDate shipmentDate;

	private LocalTime shipmentTime;

	@NotEmpty(message = "Address must not be empty")
	private String address;

	@NotBlank(message = "City must not be blank")
	private String city;

	@NotBlank(message = "State must not be blank")
	private String state;

	@NotBlank(message = "Country must not be blank")
	private String country;

	@NotBlank(message = "Zipcode must not be blank")
	private String zipCode;

	private Customers customers;

}
