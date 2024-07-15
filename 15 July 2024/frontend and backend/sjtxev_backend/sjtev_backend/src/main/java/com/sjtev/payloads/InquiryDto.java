package com.sjtev.payloads;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.sjtev.entities.CustomerContact;
import com.sjtev.entities.Vehicle;

import jakarta.validation.constraints.NotBlank;
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

public class InquiryDto {

	private Long inquiryId;

	@NotBlank(message = "Please enter full name.")
	private String fullName;

	@NotBlank(message = "Please enter mobile number.")
	private String mobileNumber;

	private String emailAddress;

	private String ownerAddress;

	private String ownerPincode;

	private String anyOtherInformationAboutInquiry;

	private LocalDate createdDate;

	private LocalTime createdTime;

	private List<Vehicle> vehicle;

	private List<CustomerContact> customerContact;

}
