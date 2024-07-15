package sjtxev.orders_orderItems.entities.entityHelper;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Embeddable
public class OrderItemPrimaryKey {

	@Column(name = "orderItemIdPK")
	private Long orderItemIdPK;

	@Column(name = "orderIdPK")
	private Long orderIdPK;

}
