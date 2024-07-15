package com.sjtev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sjtev.entities.CustomerContact;
import com.sjtev.entities.helper.CustomerContactPrimaryKey;
import com.sjtev.enums.Category;

import java.time.LocalDate;
import java.util.List;
import com.sjtev.entities.Inquiry;

public interface CustomerContactRepository extends JpaRepository<CustomerContact, CustomerContactPrimaryKey> {

	@Query("SELECT MAX(c.customerContactId.customerContactIdPK) FROM CustomerContact c WHERE c.customerContactId.inquiryIdPK = :inquiryIdPK")
	Long findMaxCustomerContactIdByInquiryId(Long inquiryId);

	List<CustomerContact> searchByCategory(Category category);

	List<CustomerContact> searchByNeedToEscalate(Boolean needToEscalate);

	List<CustomerContact> findByCreatedDateBefore(LocalDate modifiedDate);

	List<CustomerContact> findByCreatedDate(LocalDate modifiedDate);

	List<CustomerContact> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

	List<CustomerContact> findByCreatedDateAfter(LocalDate modifiedDate);

	List<CustomerContact> findByInquiry(Inquiry inquiries);

}
