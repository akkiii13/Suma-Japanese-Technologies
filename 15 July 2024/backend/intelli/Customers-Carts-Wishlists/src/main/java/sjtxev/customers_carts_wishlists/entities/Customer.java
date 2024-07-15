package sjtxev.customers_carts_wishlists.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(length = 30)
	private String firstName;

	@Column(length = 30)
	private String lastName;

	@Column(length = 14, nullable = false)
	private String mobileNumber;

	private String email;

	@Column(length = 30, nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean isActive;

	private List<Long> cartId;

	private List<Long> wishlistId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Customer customers))
			return false;
		if (!getCustomerId().equals(customers.getCustomerId()))
			return false;
		if (!getFirstName().equals(customers.getFirstName()))
			return false;
		if (!getLastName().equals(customers.getLastName()))
			return false;
		if (!getMobileNumber().equals(customers.getMobileNumber()))
			return false;
		if (getEmail() != null ? !getEmail().equals(customers.getEmail()) : customers.getEmail() != null)
			return false;
		if (!getPassword().equals(customers.getPassword()))
			return false;
		return getIsActive().equals(customers.getIsActive());
	}

	@Override
	public int hashCode() {
		int result = getCustomerId().hashCode();
		result = 31 * result + getFirstName().hashCode();
		result = 31 * result + getLastName().hashCode();
		result = 31 * result + getMobileNumber().hashCode();
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + getPassword().hashCode();
		result = 31 * result + getIsActive().hashCode();
		return result;
	}
}
