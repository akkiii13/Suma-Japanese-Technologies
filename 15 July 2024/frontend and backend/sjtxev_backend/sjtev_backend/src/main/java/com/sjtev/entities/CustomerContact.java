package com.sjtev.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjtev.entities.helper.CustomerContactPrimaryKey;
import com.sjtev.enums.Category;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "customerContacts")
public class CustomerContact {

	@EmbeddedId
	private CustomerContactPrimaryKey customerContactId;

	@Lob
	@Column
	private String customerAnswer;
	
	private Boolean needToEscalate;
	
	@Lob
	@Column
	private String escalationMessage;

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

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CustomerContact customerContact))
			return false;
		if (!getCustomerContactId().equals(customerContact.getCustomerContactId()))
			return false;
		if (!getCustomerAnswer().equals(customerContact.getCustomerAnswer()))
			return false;
		if (!getNeedToEscalate().equals(customerContact.getNeedToEscalate()))
			return false;
		if (!getCategory().equals(customerContact.getCategory()))
			return false;
		if (!getCreatedDate().equals(customerContact.getCreatedDate()))
			return false;
		if (!getCreatedDate().equals(customerContact.getCreatedDate()))
			return false;
		return true;
	}

	@Override
	public final int hashCode() {
		int result = getCustomerContactId().hashCode();
		if (getCustomerAnswer() != null)
			result = 31 * result + getCustomerAnswer().hashCode();
		if (getNeedToEscalate() != null)
			result = 31 * result + getNeedToEscalate().hashCode();
		if (getCategory() != null)
			result = 31 * result + getCategory().hashCode();
		if (getCreatedDate() != null)
			result = 31 * result + getCreatedDate().hashCode();
		if (getCreatedDate() != null)
			result = 31 * result + getCreatedDate().hashCode();
		return result;
	}

}
