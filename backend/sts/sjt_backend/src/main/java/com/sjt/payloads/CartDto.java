package com.sjt.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sjt.entities.Customers;
import com.sjt.entities.Products;
import com.sjt.entities.entityHelper.CartPrimaryKey;

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

public class CartDto {

	private CartPrimaryKey cartId;

	private LocalDate modifiedDate;

	private LocalTime modifiedTime;

	@NotNull(message = "Quantity must not be blank")
	private Integer quantity;

	private Customers customers;

	@NotNull(message = "Product must not be null")
	private Products products;

}
