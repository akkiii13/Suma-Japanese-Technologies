package com.sjt.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjt.entities.entityHelper.CartPrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table(name = "cart")
public class Cart {

	@EmbeddedId
	private CartPrimaryKey cartId;

	@Column
	private LocalDate modifiedDate;

	@Column
	private LocalTime modifiedTime;

	@Column
	private Integer quantity;

	@ManyToOne
	@MapsId("customerIdPK")
	@JoinColumn(name = "customerId")
	@JsonIgnore
	private Customers customers;

	@ManyToOne
	@JoinColumn(name = "productId")
	@JsonIgnore
	private Products products;

//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//		Cart cart = (Cart) o;
//		if (cartId == null || cart.cartId == null) {
//			return cartId == cart.cartId;
//		}
//		return cartId.equals(cart.cartId) && modifiedDate.equals(cart.modifiedDate) && quantity.equals(cart.quantity);
//	}
//
//	@Override
//	public int hashCode() {
//		return cartId != null ? cartId.hashCode() : 0;
////		return Objects.hash(cartId, modifiedDate, quantity);
//	}

}
