package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.ContactUs;

public interface ContactUsRepo extends JpaRepository<ContactUs, Integer> {

	List<ContactUs> findByDateSubmitted(LocalDate date);

	List<ContactUs> findByDateSubmittedBetween(LocalDate startDate, LocalDate endDate);

	List<ContactUs> findByDateSubmittedBefore(LocalDate date);

	List<ContactUs> findByDateSubmittedAfter(LocalDate date);

}
