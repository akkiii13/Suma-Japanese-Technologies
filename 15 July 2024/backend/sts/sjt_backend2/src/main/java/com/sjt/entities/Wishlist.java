package com.sjt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjt.entities.entityHelper.WishlistPrimaryKey;

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
@Table(name = "wishlist")
public class Wishlist {

	@EmbeddedId
	private WishlistPrimaryKey wishlistId;

	@ManyToOne
	@MapsId("customerIdPK")
	@JoinColumn(name = "customerId")
	@JsonIgnore
	private Customers customers;

	@ManyToOne
	@JoinColumn(name = "productId")
	@JsonIgnore
	private Products products;

}
