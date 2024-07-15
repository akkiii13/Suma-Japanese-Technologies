package sjtxev.contact_us.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name = "contactUs")
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactUsId;

	@Column(length = 50, nullable = false)
	private String fullName;

	@Column(length = 50, nullable = false)
	private String mobileNumber;

	@Column(length = 50)
	private String email;

	@Column(length = 50, nullable = false)
	private String subject;

	@Lob
	@Column(length = 50, nullable = false)
	private String message;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate modifiedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "UTC+5:30")
	@Temporal(TemporalType.TIME)
	private LocalTime modifiedTime;

	private Long customerId;

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ContactUs contactUs))
			return false;
		if (!getContactUsId().equals(contactUs.getContactUsId()))
			return false;
		if (!getFullName().equals(contactUs.getFullName()))
			return false;
		if (!getMobileNumber().equals(contactUs.getMobileNumber()))
			return false;
		if (!getEmail().equals(contactUs.getEmail()))
			return false;
		if (!getSubject().equals(contactUs.getSubject()))
			return false;
		if (!getMessage().equals(contactUs.getMessage()))
			return false;
		if (!getModifiedDate().equals(contactUs.getModifiedDate()))
			return false;
		return getModifiedTime().equals(contactUs.getModifiedTime());
	}

	@Override
	public final int hashCode() {
		int result = getContactUsId().hashCode();
		if (getFullName() != null)
			result = 31 * result + getFullName().hashCode();
		if (getMobileNumber() != null)
			result = 31 * result + getMobileNumber().hashCode();
		if (getEmail() != null)
			result = 31 * result + getEmail().hashCode();
		if (getSubject() != null)
			result = 31 * result + getSubject().hashCode();
		if (getMessage() != null)
			result = 31 * result + getMessage().hashCode();
		if (getModifiedDate() != null)
			result = 31 * result + getModifiedDate().hashCode();
		if (getModifiedTime() != null)
			result = 31 * result + getModifiedTime().hashCode();
		return result;
	}

}
