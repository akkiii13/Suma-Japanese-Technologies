package sjtxev.productsImages.payloads;

import sjtxev.productsImages.entities.entityHelper.ProductImagePrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ProductImageDto {

	private ProductImagePrimaryKey productImageId;

	private String name;

	private String type;

	private byte[] imageData;

	private Long productId;

}
