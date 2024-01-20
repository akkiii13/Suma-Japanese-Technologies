package com.sjt.payloads;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sjt.entities.Customers;
import com.sjt.entities.Payment;
import com.sjt.entities.Shipment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrdersDto {

	private Long orderId;

	private LocalDate orderDate;

	private LocalTime orderTime;

	@NotNull(message = "Total amount must not be null")
	private BigDecimal totalAmount;

	private Customers customers;

	@NotNull(message = "Payment must not be null")
	private Payment payment;

	@NotNull(message = "Shipment must not be null")
	private Shipment shipment;

}
