package com.sjtev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtev.entities.Inquiry;
import java.util.List;
import java.time.LocalDate;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

	List<Inquiry> searchByFullNameContainingOrMobileNumberContainingOrEmailAddressContainingAllIgnoringCase(
			String fullName, String mobileNumber, String emailAddress);

	Boolean existsByMobileNumber(String mobileNumber);

	List<Inquiry> findByCreatedDateBefore(LocalDate latestModifiedDate);

	List<Inquiry> findByCreatedDate(LocalDate latestModifiedDate);

	List<Inquiry> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

	List<Inquiry> findByCreatedDateAfter(LocalDate latestModifiedDate);

}
