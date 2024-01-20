package sjtxev.orders_orderItems.payloads;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

public class OrderDto {

	private Long orderId;

	private LocalDate orderDate;

	private LocalTime orderTime;

	@NotNull(message = "Total amount must not be null")
	private BigDecimal totalAmount;

	private Long customerId;

	@NotNull(message = "Payment id must not be null")
	private Long paymentId;

	@NotNull(message = "Shipment id must not be null")
	private Long shipmentId;

}
