package com.sjt.payloads;

import java.math.BigDecimal;

import com.sjt.entities.Orders;
import com.sjt.entities.Products;
import com.sjt.entities.entityHelper.OrderItemPrimaryKey;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderItemDto {

	private OrderItemPrimaryKey orderItemId;

	@NotNull(message = "Quantity cannot be null")
	private Integer quantity;

	@NotNull(message = "Price cannot be null")
	private BigDecimal price;

	@NotNull
	private Orders orders;

	@NotNull
	private Products products;

}
