package sjtxev.shipments.services.implementations;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import sjtxev.shipments.entities.Shipment;
import sjtxev.shipments.exceptions.ResourceNotFoundException;
import sjtxev.shipments.payloads.ShipmentDto;
import sjtxev.shipments.repositories.ShipmentRepository;
import sjtxev.shipments.services.CustomersClient;
import sjtxev.shipments.services.ShipmentService;

@Service
@Transactional
public class ShipmentServiceImplementation implements ShipmentService {

	@Autowired
	ShipmentRepository shipmentRepository;

	@Autowired
	CustomersClient customersClient;

	@Autowired
	ModelMapper modelMapper;

	@PersistenceContext
	private EntityManager entityManager;

	// add shipment
	@Override
	public ShipmentDto addShipment(@Valid ShipmentDto shipmentDto, Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		Boolean existsOrNot = this.customersClient.existsByCustomerId(customerId);

		if (!existsOrNot)
			throw new ResourceNotFoundException("Customer", "customer id", Long.toString(customerId));

		Shipment shipment = this.mapToShipment(shipmentDto);

		shipment.setShipmentDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		shipment.setShipmentTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		shipment.setCustomerId(customerId);

		Shipment savedshipment = this.shipmentRepository.save(shipment);

		return this.mapToShipmentDto(savedshipment);

	}

	// update shipment by shipment id
	@Override
	public ShipmentDto updateShipmentByShipmentId(@Valid ShipmentDto shipmentDto, Long shipmentId) {

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id must be provided.");

		Shipment shipment = this.shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Shipment", "shipment id", Long.toString(shipmentId)));

		shipment.setAddress(shipmentDto.getAddress());
		shipment.setCity(shipmentDto.getCity());
		shipment.setCountry(shipmentDto.getCountry());
		shipment.setShipmentDate(LocalDate.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		shipment.setShipmentTime(LocalTime.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		shipment.setState(shipmentDto.getState());
		shipment.setZipCode(shipmentDto.getZipCode());

		Shipment updatedShipment = this.shipmentRepository.save(shipment);

		return this.mapToShipmentDto(updatedShipment);

	}

	// delete single shipment by shipment id
	@Override
	public void deleteSingleShipmentByShipmentId(Long shipmentId) {

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id cannot be null");

		Shipment shipment = this.shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Shipment", "shipment id", Long.toString(shipmentId)));

		this.shipmentRepository.delete(shipment);

	}

	// delete list of shipments by customer id
	@Override
	public void deleteListOfShipmentsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		List<Shipment> findByCustomerId = this.shipmentRepository.findByCustomerId(customerId);

		if (findByCustomerId.isEmpty())
			throw new ResourceNotFoundException("Shipment", "customer id", String.valueOf(customerId));

		findByCustomerId.stream().forEach(wishlist -> this.shipmentRepository.delete(wishlist));

	}

	// get all shipments
	@Override
	public List<ShipmentDto> getAllShipments() {

		List<Shipment> shipmentList = this.shipmentRepository.findAll();

		if (shipmentList.isEmpty())
			throw new ResourceNotFoundException("Shipment list", "", "");

		return shipmentList.stream().map(this::mapToShipmentDto).collect(Collectors.toList());

	}

	// get list of shipments by customer id
	@Override
	public List<ShipmentDto> getListOfShipmentsByCustomerId(Long customerId) {

		if (customerId == null)
			throw new IllegalArgumentException("Customer id must be provided.");

		List<Shipment> findByCustomer = this.shipmentRepository.findByCustomerId(customerId);

		if (findByCustomer.isEmpty())
			throw new ResourceNotFoundException("Shipment", "customer id", String.valueOf(customerId));

		return findByCustomer.stream().map(this::mapToShipmentDto).collect(Collectors.toList());

	}

	// get single shipment by shipment id
	@Override
	public ShipmentDto getSingleShipmentByShipmentId(Long shipmentId) {

		if (shipmentId == null)
			throw new IllegalArgumentException("Shipment id must be provided.");

		Shipment shipment = this.shipmentRepository.findById(shipmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Shipment", "shipment id", Long.toString(shipmentId)));

		return this.mapToShipmentDto(shipment);

	}

	// exists single shipment by shipment id and customer id
	@Override
	public Boolean existsSingleShipmentByShipmentIdAndCustomerId(Long shipmentId, Long customerId) {

		return this.shipmentRepository.existsByShipmentIdAndCustomerId(shipmentId, customerId);

	}

	private ShipmentDto mapToShipmentDto(Shipment shipment) {
		return this.modelMapper.map(shipment, ShipmentDto.class);
	}

	private Shipment mapToShipment(ShipmentDto shipmentDto) {
		return this.modelMapper.map(shipmentDto, Shipment.class);
	}

}
