package com.sjtev.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sjtev.entities.Inquiry;
import com.sjtev.entities.helper.CustomerContactPrimaryKey;
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

public class CustomerContactDto {

	private CustomerContactPrimaryKey customerContactId;

	@NotBlank(message = "Please enter customer feedback.")
	private String customerAnswer;

	@NotNull
	private Boolean needToEscalate;

	private String escalationMessage;

	@NotNull
	private Category category;

	private LocalDate createdDate;

	private LocalTime createdTime;

	@NotNull(message = "Inquiry id can not be null")
	private Inquiry inquiry;

}
