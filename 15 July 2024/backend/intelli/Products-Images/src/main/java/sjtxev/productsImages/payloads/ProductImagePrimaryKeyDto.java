package sjtxev.productsImages.payloads;

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

public class ProductImagePrimaryKeyDto {

	private Long productImageIdPK;

	private Long productIdPK;

}
