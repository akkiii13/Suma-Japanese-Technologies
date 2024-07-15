package sjtxev.payments.payloads;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
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

public class PaymentDto {

	private Long paymentId;

	private LocalDate paymentDate;

	private LocalTime paymentTime;

	@NotBlank(message = "payment method cannot be blank")
	private String paymentMethod;

	@NotNull(message = "Amount cannot be empty")
	private BigDecimal amount;

	private Long customerId;

}
