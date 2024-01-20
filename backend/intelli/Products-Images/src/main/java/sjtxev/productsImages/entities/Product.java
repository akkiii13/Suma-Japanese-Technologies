package sjtxev.productsImages.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Product {

	private Long productId;

	private String brandName;

	private String modelName;

	private Double price;

	private String description;

	private Integer quantity;

	private Boolean availability;

	private Long categoryId;

	private List<Long> productImage;

}
