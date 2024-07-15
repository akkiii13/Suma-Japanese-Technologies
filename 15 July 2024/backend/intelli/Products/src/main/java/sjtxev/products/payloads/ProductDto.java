package sjtxev.products.payloads;

import jakarta.validation.constraints.NotEmpty;
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

public class ProductDto {

	private Long productId;

	@NotEmpty(message = "Brand name must not be empty")
	private String brandName;

	@NotEmpty(message = "Model name must not be empty")
	private String modelName;

	@NotNull(message = "price must not be empty")
	private Double price;

	@NotEmpty(message = "Description must not be empty")
	private String description;

	@NotNull(message = "Availability must not be empty")
	private Boolean availability;

	@NotNull(message = "Quatity must not be empty")
	private Integer quantity;

//	@NotNull(message = "Category id must not be empty")
	private Long categoryId;

}
