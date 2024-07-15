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
import com.sjtev.entities.Vehicle;
import com.sjtev.entities.helper.VehicleIdPrimaryKey;
import com.sjtev.enums.Category;
import com.sjtev.exceptions.ResourceNotFoundException;
import com.sjtev.payloads.VehicleDto;
import com.sjtev.repositories.InquiryRepository;
import com.sjtev.repositories.VehicleRepository;
import com.sjtev.services.VehicleService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VehicleServiceImplementation implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private InquiryRepository inquiryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public VehicleDto saveVehicle(VehicleDto vehicleDto, Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		if (this.inquiryRepository.existsById(inquiryId))
			throw new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId));

		Vehicle vehicle = this.mapToVehicle(vehicleDto);

		Long findMaxVehicleId = this.vehicleRepository.findMaxVehicleIdByInquiryId(inquiryId);
		Long maxVehicleId = (findMaxVehicleId != null) ? findMaxVehicleId + 1 : 1;

		VehicleIdPrimaryKey vehicleIdPrimaryKey = new VehicleIdPrimaryKey();
		vehicleIdPrimaryKey.setInquiryIdPK(inquiryId);
		vehicleIdPrimaryKey.setVehicleIdPK(maxVehicleId);

		vehicle.setVehicleId(vehicleIdPrimaryKey);
		vehicle.setCategory(Category.NOT_SURE);
		vehicle.setCreatedDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		vehicle.setCreatedTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));

		return null;
	}

	@Override
	public VehicleDto updateVehicleByVehicleIdAndInquiryId(VehicleDto vehicleDto, Long vehicleId, Long inquiryId) {

		if (vehicleId == null)
			throw new IllegalArgumentException("Vehicle id can not be null");
		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		if (this.inquiryRepository.existsById(inquiryId))
			throw new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId));

		VehicleIdPrimaryKey vehicleIdPrimaryKey = new VehicleIdPrimaryKey();
		vehicleIdPrimaryKey.setInquiryIdPK(inquiryId);
		vehicleIdPrimaryKey.setVehicleIdPK(vehicleId);

		Vehicle vehicle = this.vehicleRepository.findById(vehicleIdPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vehicle id",
						String.valueOf(vehicleId) + " and inquiry id ; " + Long.toString(inquiryId)));

		if (vehicleDto.getBrand() != null)
			vehicle.setBrand(vehicleDto.getBrand());
		if (vehicleDto.getModel() != null)
			vehicle.setModel(vehicleDto.getModel());
		if (vehicleDto.getModelYear() != null)
			vehicle.setModelYear(vehicleDto.getModelYear());
		if (vehicleDto.getTransmission() != null)
			vehicle.setTransmission(vehicleDto.getTransmission());
		if (vehicleDto.getFuel() != null)
			vehicle.setFuel(vehicleDto.getFuel());
		if (vehicleDto.getCarColour() != null)
			vehicle.setCarColour(vehicleDto.getCarColour());
		if (vehicleDto.getRegistrationNumber() != null)
			vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
		if (vehicleDto.getRegistrationDate() != null)
			vehicle.setRegistrationDate(vehicleDto.getRegistrationDate());
		if (vehicleDto.getChassisNumber() != null)
			vehicle.setChassisNumber(vehicleDto.getChassisNumber());
		if (vehicleDto.getMotorNumber() != null)
			vehicle.setMotorNumber(vehicleDto.getMotorNumber());
		if (vehicleDto.getExpectedConversionDate() != null)
			vehicle.setExpectedConversionDate(vehicleDto.getExpectedConversionDate());
		if (vehicleDto.getRtoLocation() != null)
			vehicle.setRtoLocation(vehicleDto.getRtoLocation());
		if (vehicleDto.getCityOfCarLocation() != null)
			vehicle.setCityOfCarLocation(vehicleDto.getCityOfCarLocation());
		if (vehicleDto.getPincodeOfCarLocation() != null)
			vehicle.setPincodeOfCarLocation(vehicleDto.getPincodeOfCarLocation());
		if (vehicleDto.getAnyOtherInformationAboutVehicle() != null)
			vehicle.setAnyOtherInformationAboutVehicle(vehicleDto.getAnyOtherInformationAboutVehicle());
		if (vehicleDto.getCategory() != null)
			vehicle.setCategory(vehicleDto.getCategory());

		Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

		return this.mapToVehicleDto(savedVehicle);

	}

	@Override
	public void deleteSingleVehicleByVehicleIdAndInquiryId(Long vehicleId, Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		if (this.inquiryRepository.existsById(inquiryId))
			throw new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId));

		VehicleIdPrimaryKey vehicleIdPrimaryKey = new VehicleIdPrimaryKey();
		vehicleIdPrimaryKey.setInquiryIdPK(inquiryId);
		vehicleIdPrimaryKey.setVehicleIdPK(vehicleId);

		Vehicle vehicle = this.vehicleRepository.findById(vehicleIdPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vehicle id",
						String.valueOf(vehicleId) + " and inquiry id ; " + Long.toString(inquiryId)));

		this.vehicleRepository.delete(vehicle);
	}

	@Override
	public void deleteListOfVehiclesByInquiryId(Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		Inquiry inquiry = this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId)));

		List<Vehicle> vehicleList = this.vehicleRepository.findByInquiry(inquiry);

		vehicleList.stream().forEach(i -> this.vehicleRepository.delete(i));

	}

	@Override
	public void deleteAllVehicles() {
		this.vehicleRepository.deleteAll();
	}

	@Override
	public VehicleDto getSingleVehicleByVehicleIdAndInquiryId(Long vehicleId, Long inquiryId) {

		if (vehicleId == null)
			throw new IllegalArgumentException("Vehicle id can not be null");
		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		if (this.inquiryRepository.existsById(inquiryId))
			throw new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId));

		VehicleIdPrimaryKey vehicleIdPrimaryKey = new VehicleIdPrimaryKey();
		vehicleIdPrimaryKey.setInquiryIdPK(inquiryId);
		vehicleIdPrimaryKey.setVehicleIdPK(vehicleId);

		Vehicle vehicle = this.vehicleRepository.findById(vehicleIdPrimaryKey)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "vehicle id",
						String.valueOf(vehicleId) + " and inquiry id ; " + Long.toString(inquiryId)));

		return this.mapToVehicleDto(vehicle);
	}

	@Override
	public List<VehicleDto> getListOfVehiclesByInquiryId(Long inquiryId) {

		if (inquiryId == null)
			throw new IllegalArgumentException("Inquiry id can not be null");

		Inquiry inquiry = this.inquiryRepository.findById(inquiryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inquiry", "inquiry id", String.valueOf(inquiryId)));

		List<Vehicle> vehicleList = this.vehicleRepository.findByInquiry(inquiry);

		if (vehicleList == null)
			throw new ResourceNotFoundException("Vehicles", "inquiry id", Long.toString(inquiryId));

		return vehicleList.stream().map(this::mapToVehicleDto).collect(Collectors.toList());

	}

	@Override
	public List<VehicleDto> getAllVehicles() {

		List<Vehicle> findAll = this.vehicleRepository.findAll();

		if (findAll == null)
			throw new ResourceNotFoundException("Vehicles", "", "");

		return findAll.stream().map(this::mapToVehicleDto).collect(Collectors.toList());

	}

	@Override
	public List<VehicleDto> searchListOfVehiclesByAnyValue(String value) {

		if (value == null || value.isBlank())
			throw new IllegalArgumentException("search value can not be blank");

//		List<Vehicle> searchedList = this.vehicleRepository.searchByCriteria(new VehicleSearchCriteria(value, value,
//				value, value, value, value, value, value, value, value, value, value, value));
//
//		if (searchedList == null)
//			throw new ResourceNotFoundException("Vehicles", "", value);
//
//		return searchedList.stream().map(this::mapToVehicleDto).collect(Collectors.toList());
		return null;

	}

	@Override
	public List<VehicleDto> searchByCreatedDateBefore(LocalDate date) {

		if (date == null)
			throw new IllegalArgumentException("Date can not be null.");

		List<Vehicle> findByCreatedDateBefore = this.vehicleRepository.findByCreatedDate(date);

		if (findByCreatedDateBefore == null)
			throw new ResourceNotFoundException("Vehicles", "before date", date.toString());

		return findByCreatedDateBefore.stream().map(this::mapToVehicleDto).collect(Collectors.toList());

	}

	@Override
	public List<VehicleDto> searchByCreatedDate(LocalDate date) {

		if (date == null)
			throw new IllegalArgumentException("Date can not be null.");

		List<Vehicle> findByCreatedDate = this.vehicleRepository.findByCreatedDate(date);

		if (findByCreatedDate == null)
			throw new ResourceNotFoundException("Vehicles", "on sprcific date", date.toString());

		return findByCreatedDate.stream().map(this::mapToVehicleDto).collect(Collectors.toList());

	}

	@Override
	public List<VehicleDto> searchByCreatedDateBetween(LocalDate startDate, LocalDate endDate) {

		if (startDate == null)
			throw new IllegalArgumentException("Start date can not be null.");
		if (endDate == null)
			throw new IllegalArgumentException("End date can not be null.");

		List<Vehicle> findByCreatedDateBetween = this.vehicleRepository.findByCreatedDateBetween(startDate, endDate);

		if (findByCreatedDateBetween == null)
			throw new ResourceNotFoundException("Vehivles", "start date",
					startDate.toString() + " and end date : " + endDate.toString() + ".");

		return findByCreatedDateBetween.stream().map(this::mapToVehicleDto).collect(Collectors.toList());

	}

	@Override
	public List<VehicleDto> searchByCreatedDateAfter(LocalDate date) {
		if (date == null)
			throw new IllegalArgumentException("Date can not be null.");

		List<Vehicle> findByCreatedDateAfter = this.vehicleRepository.findByCreatedDateAfter(date);

		if (findByCreatedDateAfter == null)
			throw new ResourceNotFoundException("Vehicles", "after date", date.toString());

		return findByCreatedDateAfter.stream().map(this::mapToVehicleDto).collect(Collectors.toList());

	}

	private VehicleDto mapToVehicleDto(Vehicle vehicle) {
		return this.modelMapper.map(vehicle, VehicleDto.class);
	}

	private Vehicle mapToVehicle(VehicleDto vehicleDto) {
		return this.modelMapper.map(vehicleDto, Vehicle.class);
	}

}
