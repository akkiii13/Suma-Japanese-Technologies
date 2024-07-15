package com.sjtev.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtev.entities.Inquiry;
import com.sjtev.exceptions.ResourceNotFoundException;
import com.sjtev.payloads.InquiryDto;
import com.sjtev.repositories.InquiryRepository;
import com.sjtev.services.InquiryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InquiryServiceImplementation implements InquiryService {

	@Autowired
	private InquiryRepository inquiryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public InquiryDto saveInquiry(InquiryDto inquiryDto) {

		if (this.inquiryRepository.existsByMobileNumber(inquiryDto.getMobileNumber()))
			throw new IllegalArgumentException("This mobile number has aleardy registered");

		Inquiry mapToInquiry = this.mapToInquiry(inquiryDto);

		mapToInquiry.setCreatedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		mapToInquiry.setCreatedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		Inquiry savedInquiry = this.inquiryRepository.save(mapToInquiry);

		return this.mapToInquiryDto(savedInquiry);

	}

	@Override
	public InquiryDto updateMobileNumberFieldByInquiryId(Long inquiryId, String mobileNumber) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");
		if (mobileNumber == null || mobileNumber.trim().isBlank())
			throw new IllegalArgumentException("Please enter mobile number");

		Inquiry inquiry = this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", Long.toString(inquiryId)));

		if (mobileNumber.equals(inquiry.getMobileNumber()))
			throw new IllegalArgumentException("This number is same as old. Please enter new number");
		if (this.inquiryRepository.existsByMobileNumber(mobileNumber))
			throw new IllegalArgumentException("This mobile number has aleardy registered with anohter id");

		inquiry.setMobileNumber(mobileNumber);

		Inquiry savedInquiry = this.inquiryRepository.save(inquiry);

		return this.mapToInquiryDto(savedInquiry);

	}

	@Override
	public InquiryDto updateRemainingInquiryFieldsByInquiryId(InquiryDto inquiryDto, Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		Inquiry inquiry = this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", Long.toString(inquiryId)));

		if (inquiryDto.getFullName() != null)
			inquiry.setFullName(inquiryDto.getFullName());
		if (inquiryDto.getEmailAddress() != null)
			inquiry.setEmailAddress(inquiryDto.getEmailAddress());
		if (inquiryDto.getOwnerAddress() != null)
			inquiry.setOwnerAddress(inquiryDto.getOwnerAddress());
		if (inquiryDto.getOwnerPincode() != null)
			inquiry.setOwnerPincode(inquiryDto.getOwnerPincode());
		if (inquiryDto.getAnyOtherInformationAboutInquiry() != null)
			inquiry.setAnyOtherInformationAboutInquiry(inquiryDto.getAnyOtherInformationAboutInquiry());

		Inquiry savedInquiry = this.inquiryRepository.save(inquiry);

		return this.mapToInquiryDto(savedInquiry);

	}

	@Override
	public void deleteSingleInquiryByInquiryId(Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		if (!(this.inquiryRepository.existsById(inquiryId)))
			throw new ResourceNotFoundException("Inquiry", "inquiry id", Long.toString(inquiryId));

		this.inquiryRepository.deleteById(inquiryId);

	}

	@Override
	public void deleteListOfSeletedInquiriesByInquiryId(List<Long> inquiryIds) {

		entityManager.flush();

		this.inquiryRepository.deleteAllById(inquiryIds);

		entityManager.flush();

	}

	@Override
	public void deleteAllInquiries() {

		this.inquiryRepository.deleteAll();

	}

	@Override
	public InquiryDto getSingleInquiryByInquiryInquiryId(Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null.");

		Inquiry inquiry = this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", Long.toString(inquiryId)));

		return this.mapToInquiryDto(inquiry);

	}

	@Override
	public List<InquiryDto> getListOfInquiriesByDateBefore(LocalDate date) {

		if (date == null)
			throw new IllegalArgumentException("Date cannot be null");

		List<Inquiry> findByDateBefore = this.inquiryRepository.findByCreatedDateBefore(date);

		if (findByDateBefore == null)
			throw new ResourceNotFoundException("Inquiries", "before date", date.toString());

		return findByDateBefore.stream().map(this::mapToInquiryDto).collect(Collectors.toList());

	}

	@Override
	public List<InquiryDto> getListOfInquiriesBySpecificDate(LocalDate specificDate) {

		if (specificDate == null)
			throw new IllegalArgumentException("Date cannot be null");

		List<Inquiry> findBySpecificDate = this.inquiryRepository.findByCreatedDate(specificDate);

		if (findBySpecificDate == null)
			throw new ResourceNotFoundException("Inquiries", "on specific date", specificDate.toString());

		return findBySpecificDate.stream().map(this::mapToInquiryDto).collect(Collectors.toList());

	}

	@Override
	public List<InquiryDto> getListOfInquiriesByRangeOfDates(LocalDate startDate, LocalDate endDate) {

		if (startDate == null)
			throw new IllegalArgumentException("Start date can not be null");

		if (endDate == null)
			throw new IllegalArgumentException("End date cannot be null");

		List<Inquiry> findByDateBetween = this.inquiryRepository.findByCreatedDateBetween(startDate, endDate);

		if (findByDateBetween == null)
			throw new ResourceNotFoundException("Inquiries", "start date",
					startDate.toString() + " and end date : " + endDate.toString());

		return findByDateBetween.stream().map(this::mapToInquiryDto).collect(Collectors.toList());

	}

	@Override
	public List<InquiryDto> getListOfInquiriesByDateAfter(LocalDate date) {

		if (date == null)
			throw new IllegalArgumentException("Date can not be null");

		List<Inquiry> findByDateAfter = this.inquiryRepository.findByCreatedDateAfter(date);

		if (findByDateAfter == null)
			throw new ResourceNotFoundException("Inquiries", "after date", date.toString());

		return findByDateAfter.stream().map(this::mapToInquiryDto).collect(Collectors.toList());

	}

	@Override
	public List<InquiryDto> getAllInquiries() {

		List<Inquiry> allInquiries = this.inquiryRepository.findAll();

		if (allInquiries == null)
			throw new ResourceNotFoundException("Inquiries", "", "");

		return allInquiries.stream().map(this::mapToInquiryDto).collect(Collectors.toList());

	}

	@Override
	public List<InquiryDto> searchByAnyValue(String searchString) {

		if (searchString == null || searchString.trim().isBlank())
			throw new IllegalArgumentException("");

		List<Inquiry> searchResult = this.inquiryRepository
				.searchByFullNameContainingOrMobileNumberContainingOrEmailAddressContainingAllIgnoringCase(searchString,
						searchString, searchString);

		if (searchResult == null)
			throw new ResourceNotFoundException("Inquiries", "", searchString);

		return searchResult.stream().map(this::mapToInquiryDto).collect(Collectors.toList());

	}

	private Inquiry mapToInquiry(InquiryDto inquiryDto) {
		return this.modelMapper.map(inquiryDto, Inquiry.class);
	}

	private InquiryDto mapToInquiryDto(Inquiry inquiry) {
		return this.modelMapper.map(inquiry, InquiryDto.class);
	}

}
