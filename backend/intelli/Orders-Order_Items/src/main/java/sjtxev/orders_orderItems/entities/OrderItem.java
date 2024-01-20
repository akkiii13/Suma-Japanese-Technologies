package sjtxev.orders_orderItems.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sjtxev.orders_orderItems.entities.entityHelper.OrderItemPrimaryKey;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

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
	private Order order;

	private Long productId;

}
