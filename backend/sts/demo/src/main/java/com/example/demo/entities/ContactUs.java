package com.example.demo.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "contactus")
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false, name = "full_name")
	private String fullName;

	@Column(length = 20, nullable = false, name = "mobile_number_prefix")
	private String mobileNumberPrefix;

	@Column(length = 20, nullable = false, name = "mobile_number")
	private String mobileNumber;

	@Column(length = 255)
	private String email;

	@Column(length = 255, nullable = false)
	private String subject;

	@Lob
	@Column(nullable = false, columnDefinition = "TEXT")
	private String message;

	@Column(nullable = false, name = "date_submitted")
	private LocalDate dateSubmitted;

	@Column(nullable = false, name = "time_submitted")
	private LocalTime timeSubmitted;

	@Override
	public int hashCode() {
		return Objects.hash(id, fullName, mobileNumber, email, subject, message, dateSubmitted, timeSubmitted);
	}

}
