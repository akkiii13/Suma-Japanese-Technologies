package com.sjtev.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sjtev.entities.Inquiry;
import com.sjtev.entities.Vehicle;
import com.sjtev.entities.helper.VehicleIdPrimaryKey;

public interface VehicleRepository extends JpaRepository<Vehicle, VehicleIdPrimaryKey> {

	@Query("SELECT MAX(v.vehicleId.vehicleIdPK) FROM Vehicle v WHERE v.vehicleId.inquiryIdPK = :inquiryIdPK")
	Long findMaxVehicleIdByInquiryId(@Param("inquiryIdPK") Long inquiryId);

	@Query(value = "SELECT * FROM vehicles v WHERE v.brand like %:searchString% OR v.model like %:searchString% OR v.modelYear like %:searchString% OR v.transmission like %:searchString% OR v.fuel like %:searchString% OR v.carColour like %:searchString% OR v.registrationNumber like %:searchString% OR v.chassisNumber like %:searchString% OR v.motorNumber like %:searchString% OR v.rtoLocation like %:searchString% OR v.cityOfCarLocation like %:searchString% OR v.pincodeOfCarLocation like %:searchString% OR v.anyOtherInformationAboutVehicle like %:searchString%", nativeQuery = true)
	List<Vehicle> findVehiclesByKeyword(@Param("searchString") String searchString);

	List<Vehicle> findByInquiry(Inquiry inquiries);

	List<Vehicle> findByCreatedDateBefore(LocalDate modifiedDate);

	List<Vehicle> findByCreatedDate(LocalDate modifiedDate);

	List<Vehicle> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

	List<Vehicle> findByCreatedDateAfter(LocalDate modifiedDate);

}
