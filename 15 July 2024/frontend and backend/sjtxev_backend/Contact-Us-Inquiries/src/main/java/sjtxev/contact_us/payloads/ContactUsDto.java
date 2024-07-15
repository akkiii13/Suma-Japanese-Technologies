package sjtxev.contact_us.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

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

public class ContactUsDto {

	private Long contactUsId;

	@NotBlank(message = "Name must not be blank")
	private String fullName;

	@NotBlank(message = "Mobile number must not be empty")
//	@Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$", message = "Mobile number must be valid")
	private String mobileNumber;

	private String email;

	@NotBlank(message = "Subject must not be blank")
	private String subject;

	@NotBlank(message = "Message must not be blank")
	private String message;

	private LocalDate modifiedDate;

	private LocalTime modifiedTime;

	private Long customerId;

}
