package com.sjt.payloads;

import com.sjt.entities.Customers;
import com.sjt.entities.Products;
import com.sjt.entities.entityHelper.WishlistPrimaryKey;

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

public class WishlistDto {

	private WishlistPrimaryKey wishlistId;

	private Customers customers;

	@NotNull(message = "Product must not be null")
	private Products products;

}
