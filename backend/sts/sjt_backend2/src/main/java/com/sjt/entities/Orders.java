package com.sjt.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private LocalDate orderDate;

	private LocalTime orderTime;

	@Column(precision = 10, scale = 2)
	private BigDecimal totalAmount;

	@ManyToOne
	@JoinColumn(name = "customerId")
	@JsonIgnore
	private Customers customers;

	@ManyToOne
	@JoinColumn(name = "paymentId")
	@JsonIgnore
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "shipmentId")
	@JsonIgnore
	private Shipment shipment;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnore
	private List<OrderItem> orderItem;

}
