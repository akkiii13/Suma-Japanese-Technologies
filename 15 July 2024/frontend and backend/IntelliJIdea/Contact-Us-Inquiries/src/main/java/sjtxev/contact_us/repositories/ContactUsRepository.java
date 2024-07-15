package sjtxev.contact_us.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sjtxev.contact_us.entities.ContactUs;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {

	// find list of contact us inquiries by customer
	List<ContactUs> findByCustomerId(Long customerId);

	// find list of contact us inquiries by mobile number
	List<ContactUs> findByMobileNumber(String mobileNumber);

	// find list of contact us inquiries by modified date
	List<ContactUs> findByModifiedDate(LocalDate modifiedDate);

	// find list of contact us inquiries by modified date between
	List<ContactUs> findByModifiedDateBetween(LocalDate startDate, LocalDate endDate);

	// find list of contact us inquiries by modified date before
	List<ContactUs> findByModifiedDateBefore(LocalDate modifiedDate);

	// find list of contact us inquiries by modified date after
	List<ContactUs> findByModifiedDateAfter(LocalDate modifiedDate);

	// find customer by customer id and contact us id
	ContactUs findByCustomerIdAndContactUsId(Long customerId, Long contactUsId);

}
