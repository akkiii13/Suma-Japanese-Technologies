package sjtxev.orders_orderItems.payloads;

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

public class OrderItemPrimaryKeyDto {

	private Long orderItemIdPK;

	private Long orderIdPK;

}
