package com.sjtev.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "inquiries")
public class Inquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inquiryId;

	@Column(length = 50)
	private String fullName;

	@Column(length = 20, unique = true)
	private String mobileNumber;

	@Column(length = 50)
	private String emailAddress;

	@Lob
	@Column(length = 50)
	private String ownerAddress;

	@Column(length = 15)
	private String ownerPincode;

	@Lob
	@Column
	private String anyOtherInformationAboutInquiry;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "UTC+5:30")
	@Temporal(TemporalType.TIME)
	private LocalTime createdTime;

	@OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Vehicle> vehicle;

	@OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<CustomerContact> customerContacts;

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Inquiry inquiry))
			return false;
		if (!getInquiryId().equals(inquiry.getInquiryId()))
			return false;
		if (!getFullName().equals(inquiry.getFullName()))
			return false;
		if (!getMobileNumber().equals(inquiry.getMobileNumber()))
			return false;
		if (!getEmailAddress().equals(inquiry.getEmailAddress()))
			return false;
		if (!getOwnerAddress().equals(inquiry.getOwnerAddress()))
			return false;
		if (!getOwnerPincode().equals(inquiry.getOwnerPincode()))
			return false;
		if (!getAnyOtherInformationAboutInquiry().equals(inquiry.getAnyOtherInformationAboutInquiry()))
			return false;
		if (!getCreatedDate().equals(inquiry.getCreatedDate()))
			return false;
		if (!getCreatedTime().equals(inquiry.getCreatedTime()))
			return false;
		return true;
	}

	@Override
	public final int hashCode() {
		int result = getInquiryId().hashCode();
		if (getFullName() != null)
			result = 31 * result + getFullName().hashCode();
		if (getMobileNumber() != null)
			result = 31 * result + getMobileNumber().hashCode();
		if (getEmailAddress() != null)
			result = 31 * result + getEmailAddress().hashCode();
		if (getOwnerAddress() != null)
			result = 31 * result + getOwnerAddress().hashCode();
		if (getOwnerPincode() != null)
			result = 31 * result + getOwnerPincode().hashCode();
		if (getAnyOtherInformationAboutInquiry() != null)
			result = 31 * result + getAnyOtherInformationAboutInquiry().hashCode();
		if (getCreatedDate() != null)
			result = 31 * result + getCreatedDate().hashCode();
		if (getCreatedTime() != null)
			result = 31 * result + getCreatedTime().hashCode();
		return result;
	}

}
