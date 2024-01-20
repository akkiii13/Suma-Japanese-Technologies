package sjtxev.customers_carts_wishlists.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerDto {

	private Long customerId;

	@NotEmpty(message = "First name must not be empty")
	private String firstName;

	@NotEmpty(message = "Last name must not be empty")
	private String lastName;

	@NotEmpty(message = "Mobile number must not be empty")
	@Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$", message = "Mobile number must be valid")
	private String mobileNumber;

	@NotEmpty(message = "Email must not be empty")
	@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is not valid")
	private String email;

	@NotEmpty(message = "Password must not be empty")
	@Size(min = 8, message = "Password must be 8 characters long")
	private String password;

	private Boolean isActive;

}
