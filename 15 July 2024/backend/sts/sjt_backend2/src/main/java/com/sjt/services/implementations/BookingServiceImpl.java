package com.sjt.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjt.entities.Booking;
import com.sjt.entities.Customers;
import com.sjt.exceptions.ResourceNotFoundException;
import com.sjt.payloads.BookingDto;
import com.sjt.repositories.BookingRepository;
import com.sjt.repositories.CustomersRepository;
import com.sjt.services.BookingService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	ModelMapper modelMapper;

	// save booking
	@Override
	public BookingDto saveBooking(@Valid BookingDto bookingDto) {
		String mobileNumber = bookingDto.getMobileNumber();

		Booking booking = this.mapToBooking(bookingDto);

		booking.setModifiedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		booking.setModifiedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		Customers customer = null;

		if (mobileNumber != null)
			customer = this.customersRepository.findByMobileNumber(mobileNumber);

		if (customer != null)
			booking.setCustomerId(customer.getCustomerId());

		Booking savedContactUs = this.bookingRepository.save(booking);

		return this.mapToBookingDto(savedContactUs);
	}

	// update booking by booking id
	@Override
	public BookingDto updateBookingByContactUsId(@Valid BookingDto bookingDto, @Valid Long bookingId) {
		if (bookingId == null)
			throw new IllegalArgumentException("Booking id cannot be null");

		Booking booking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "booking id", Long.toString(bookingId)));

		booking.setFullName(bookingDto.getFullName());
		booking.setMobileNumber(bookingDto.getMobileNumber());
		booking.setEmailAddress(bookingDto.getEmailAddress());
		booking.setOwnerAddress(bookingDto.getOwnerAddress());
		booking.setOwnerPincode(bookingDto.getOwnerPincode());
		booking.setBrand(bookingDto.getBrand());
		booking.setModel(bookingDto.getModel());
		booking.setModelYear(bookingDto.getModelYear());
		booking.setTransmission(bookingDto.getTransmission());
		booking.setFuel(bookingDto.getFuel());
		booking.setCarColour(bookingDto.getCarColour());
		booking.setRegistrationNumber(bookingDto.getRegistrationNumber());
		booking.setRegistrationDate(bookingDto.getRegistrationDate());
		booking.setChassisNumber(bookingDto.getChassisNumber());
		booking.setMotorNumber(bookingDto.getMotorNumber());
		booking.setExpectedConversionDate(bookingDto.getExpectedConversionDate());
		booking.setCityOfCarLocation(bookingDto.getCityOfCarLocation());
		booking.setPincodeOfCarLocation(bookingDto.getPincodeOfCarLocation());
		booking.setAnyOtherInformation(bookingDto.getAnyOtherInformation());
		booking.setModifiedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		booking.setModifiedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		Booking updatedbooking = this.bookingRepository.save(booking);

		return this.mapToBookingDto(updatedbooking);
	}

	// delete single booking by booking id
	@Override
	public void deleteSingleBookingByBookingId(Long bookingId) {
		if (bookingId == null)
			throw new IllegalArgumentException("Booking id cannot be null");

		Booking booking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "booking id", Long.toString(bookingId)));

		this.bookingRepository.delete(booking);
	}

	// delete list of bookings by customer id
	@Override
	public void deleteListOfBookingsByCustomerId(Long customerId) {
		if (customerId == null)
			throw new IllegalArgumentException("Customer id cannot be null");

		List<Booking> bookings = this.bookingRepository.findByCustomerId(customerId);

		if (bookings.isEmpty())
			throw new ResourceNotFoundException("Bookings", "customer id", String.valueOf(customerId));

		bookings.stream().forEach((i) -> this.bookingRepository.delete(i));
	}

	// delete all bookings
	@Override
	public void deleteAllBookings() {
		this.bookingRepository.deleteAll();
	}

	// get single booking by booking id
	@Override
	public BookingDto getSingleBookingByBookingId(Long bookingId) {
		if (bookingId == null)
			throw new IllegalArgumentException("Booking id cannot be null");

		Booking booking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "contact us id", Long.toString(bookingId)));

		return this.mapToBookingDto(booking);
	}

	// get all bookings
	@Override
	public List<BookingDto> getAllBookings() {
		List<Booking> bookingList = this.bookingRepository.findAll();

		if (bookingList.isEmpty())
			throw new ResourceNotFoundException("Bookings", "", "");

		return bookingList.stream().sorted(
				Comparator.comparing(Booking::getModifiedDate).thenComparing(Booking::getModifiedTime).reversed())
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	// get list of bookings by customer id
	@Override
	public List<BookingDto> getListOfBookingsByCustomerId(Long customerId) {
		if (customerId == null)
			throw new IllegalArgumentException("Customer id cannot be null");

		List<Booking> findByCustomers = this.bookingRepository.findByCustomerId(customerId);

		if (findByCustomers.isEmpty())
			throw new ResourceNotFoundException("Bookings", "customer id", String.valueOf(customerId));

		return findByCustomers.stream()
				.sorted(Comparator.comparing(Booking::getModifiedDate).reversed()
						.thenComparing(Booking::getModifiedTime).reversed())
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	// get single booking by customer id and booking id
	@Override
	public BookingDto getSingleBookingByCustomerIdAndBookingId(Long customerId, Long bookingId) {
		if (customerId == null)
			throw new IllegalArgumentException("Customer id cannot be null");

		if (bookingId == null)
			throw new IllegalArgumentException("Booking id cannot be null");

		Booking booking = this.bookingRepository.findByCustomerIdAndBookingId(customerId, bookingId);

		if (booking == null)
			throw new ResourceNotFoundException("Bookings", "customer id",
					String.valueOf(customerId) + " and booking id : " + Long.toString(bookingId));

		return this.mapToBookingDto(booking);
	}

	// get list of bookings by mobile number
	@Override
	public List<BookingDto> getListOfBookingsByMobileNumber(String mobileNumber) {
		if (mobileNumber.isBlank())
			throw new IllegalArgumentException("Mobile number cannot be blank");

		List<Booking> bookingList = this.bookingRepository.findByMobileNumber(mobileNumber);

		if (bookingList.isEmpty())
			throw new ResourceNotFoundException("Bookings", "Mobile Number", mobileNumber);

		return bookingList.stream()
				.sorted(Comparator.comparing(Booking::getModifiedDate).reversed()
						.thenComparing(Booking::getModifiedTime).reversed())
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	// get list of bookings by date
	@Override
	public List<BookingDto> getListOfBookingsByDate(LocalDate date) {
		if (date == null)
			throw new IllegalArgumentException("Date must be provided.");

		List<Booking> bookingList = this.bookingRepository.findByModifiedDate(date);

		if (bookingList.isEmpty())
			throw new ResourceNotFoundException("Bookings", "date", String.valueOf(date));

		return bookingList.stream().sorted(Comparator.comparing(Booking::getModifiedTime).reversed())
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	// get list of bookings by range of date
	@Override
	public List<BookingDto> getListOfBookingsByRangeOfDate(LocalDate startDate, LocalDate endDate) {
		if (startDate == null || endDate == null)
			throw new IllegalArgumentException("Start date and end date must be provided.");

		List<Booking> bookingList = this.bookingRepository.findByModifiedDateBetween(startDate, endDate);

		if (bookingList.isEmpty())
			throw new ResourceNotFoundException("Bookings", "start date",
					String.valueOf(startDate) + " and end date : " + String.valueOf(endDate));

		return bookingList.stream().sorted(
				Comparator.comparing(Booking::getModifiedDate).thenComparing(Booking::getModifiedTime).reversed())
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	// get list of bookings by date before
	@Override
	public List<BookingDto> getListOfBookingsBeforeDate(LocalDate modifiedDate) {
		if (modifiedDate == null)
			throw new IllegalArgumentException("Date must be provided.");

		List<Booking> contactUsList = this.bookingRepository.findByModifiedDateBefore(modifiedDate);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Bookings", "date before", String.valueOf(modifiedDate));

		return contactUsList.stream()
				.sorted(Comparator.comparing(Booking::getModifiedDate).reversed()
						.thenComparing(Booking::getModifiedTime).reversed())
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	// get list of bookings by date after
	@Override
	public List<BookingDto> getListOfBookingsAfterDate(LocalDate modifiedDate) {
		if (modifiedDate == null)
			throw new IllegalArgumentException("Date must be provided.");

		List<Booking> contactUsList = this.bookingRepository.findByModifiedDateAfter(modifiedDate);

		if (contactUsList.isEmpty())
			throw new ResourceNotFoundException("Bookings", "date after", String.valueOf(modifiedDate));

		return contactUsList.stream()
				.sorted(Comparator.comparing(Booking::getModifiedDate).thenComparing(Booking::getModifiedTime))
				.map(this::mapToBookingDto).collect(Collectors.toList());
	}

	private BookingDto mapToBookingDto(Booking booking) {
		return this.modelMapper.map(booking, BookingDto.class);
	}

	private Booking mapToBooking(BookingDto bookingDto) {
		return this.modelMapper.map(bookingDto, Booking.class);
	}

}
