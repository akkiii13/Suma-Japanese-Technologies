package sjtxev.bookings.entities;

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
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@Column(length = 50, nullable = false)
	private String fullName;

	@Column(length = 20, nullable = false)
	private String mobileNumber;

	@Column(length = 50)
	private String email;

	@Lob
	@Column(length = 50)
	private String ownerAddress;

	@Column(length = 15)
	private String ownerPincode;

	@Column(length = 50)
	private String brand;

	@Column(length = 50)
	private String model;

	@Column(length = 50)
	private String modelYear;

	@Column(length = 50)
	private String transmission;

	@Column(length = 50)
	private String fuel;

	@Column(length = 50)
	private String carColour;

	@Column(length = 50)
	@Pattern(regexp = "/^([A-Za-z]{2}[0-9]{2}[A-Za-z]{2}[0-9]{4})$/", message = "Registration number is not valid")
	private String registrationNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate registrationDate;

	@Column(length = 50)
	private String chassisNumber;

	@Column(length = 50)
	private String motorNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC+5:30")
	@Temporal(TemporalType.DATE)
	private LocalDate expectedConversionDate;

	@Column(length = 50)
	private String cityOfCarLocation;

	@Column(length = 50)
	private String pincodeOfCarLocation;

	@Lob
	@Column
	private String anyOtherInformation;

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
		if (!(o instanceof Booking booking))
			return false;
		if (!getBookingId().equals(booking.getBookingId()))
			return false;
		if (!getFullName().equals(booking.getFullName()))
			return false;
		if (!getMobileNumber().equals(booking.getMobileNumber()))
			return false;
		if (!getEmail().equals(booking.getEmail()))
			return false;
		if (!getOwnerAddress().equals(booking.getOwnerAddress()))
			return false;
		if (!getOwnerPincode().equals(booking.getOwnerPincode()))
			return false;
		if (!getBrand().equals(booking.getBrand()))
			return false;
		if (!getModel().equals(booking.getModel()))
			return false;
		if (!getModelYear().equals(booking.getModelYear()))
			return false;
		if (!getTransmission().equals(booking.getTransmission()))
			return false;
		if (!getFuel().equals(booking.getFuel()))
			return false;
		if (!getCarColour().equals(booking.getCarColour()))
			return false;
		if (!getRegistrationNumber().equals(booking.getRegistrationNumber()))
			return false;
		if (!getRegistrationDate().equals(booking.getRegistrationDate()))
			return false;
		if (!getChassisNumber().equals(booking.getChassisNumber()))
			return false;
		if (!getMotorNumber().equals(booking.getMotorNumber()))
			return false;
		if (!getExpectedConversionDate().equals(booking.getExpectedConversionDate()))
			return false;
		if (!getCityOfCarLocation().equals(booking.getCityOfCarLocation()))
			return false;
		if (!getPincodeOfCarLocation().equals(booking.getPincodeOfCarLocation()))
			return false;
		if (!getAnyOtherInformation().equals(booking.getAnyOtherInformation()))
			return false;
		if (!getModifiedDate().equals(booking.getModifiedDate()))
			return false;
		if (!getModifiedTime().equals(booking.getModifiedTime()))
			return false;
		return getModifiedTime().equals(booking.getModifiedTime());
	}

	@Override
	public final int hashCode() {
		int result = getBookingId().hashCode();
		if (getFullName() != null)
			result = 31 * result + getFullName().hashCode();
		if (getMobileNumber() != null)
			result = 31 * result + getMobileNumber().hashCode();
		if (getEmail() != null)
			result = 31 * result + getEmail().hashCode();
		if (getOwnerAddress() != null)
			result = 31 * result + getOwnerAddress().hashCode();
		if (getOwnerPincode() != null)
			result = 31 * result + getOwnerPincode().hashCode();
		if (getBrand() != null)
			result = 31 * result + getBrand().hashCode();
		if (getModel() != null)
			result = 31 * result + getModel().hashCode();
		if (getModelYear() != null)
			result = 31 * result + getModelYear().hashCode();
		if (getTransmission() != null)
			result = 31 * result + getTransmission().hashCode();
		if (getFuel() != null)
			result = 31 * result + getFuel().hashCode();
		if (getCarColour() != null)
			result = 31 * result + getCarColour().hashCode();
		if (getRegistrationNumber() != null)
			result = 31 * result + getRegistrationNumber().hashCode();
		if (getRegistrationDate() != null)
			result = 31 * result + getRegistrationDate().hashCode();
		if (getChassisNumber() != null)
			result = 31 * result + getChassisNumber().hashCode();
		if (getMotorNumber() != null)
			result = 31 * result + getMotorNumber().hashCode();
		if (getExpectedConversionDate() != null)
			result = 31 * result + getExpectedConversionDate().hashCode();
		if (getCityOfCarLocation() != null)
			result = 31 * result + getCityOfCarLocation().hashCode();
		if (getPincodeOfCarLocation() != null)
			result = 31 * result + getPincodeOfCarLocation().hashCode();
		if (getAnyOtherInformation() != null)
			result = 31 * result + getAnyOtherInformation().hashCode();
		if (getModifiedDate() != null)
			result = 31 * result + getModifiedDate().hashCode();
		if (getModifiedTime() != null)
			result = 31 * result + getModifiedTime().hashCode();
		return result;
	}

}
