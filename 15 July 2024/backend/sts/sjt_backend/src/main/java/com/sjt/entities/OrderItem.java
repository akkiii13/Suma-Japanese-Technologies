package com.sjt.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjt.entities.entityHelper.OrderItemPrimaryKey;

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
@Table(name = "orderItem")
public class OrderItem {

	@EmbeddedId
	private OrderItemPrimaryKey orderItemId;

	private Integer quantity;

	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	@ManyToOne
	@MapsId("orderIdPK")
	@JoinColumn(name = "orderId")
	@JsonIgnore
	private Orders orders;

	@ManyToOne
	@JoinColumn(name = "productId")
	@JsonIgnore
	private Products products;

}
