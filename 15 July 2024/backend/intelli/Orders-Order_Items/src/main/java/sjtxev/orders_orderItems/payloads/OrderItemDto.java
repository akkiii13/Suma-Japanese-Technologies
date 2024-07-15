package sjtxev.orders_orderItems.payloads;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sjtxev.orders_orderItems.entities.Order;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderItemDto {

	private OrderItemPrimaryKeyDto orderItemId;

	@NotNull(message = "Quantity cannot be null")
	private Integer quantity;

	@NotNull(message = "Price cannot be null")
	private BigDecimal price;

	@NotNull
	private Order order;

	@NotNull
	private Long productId;

}
