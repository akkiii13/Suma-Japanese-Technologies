package sjtxev.productsImages.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductImageResponse {

	private String name;

	private String url;

	private String type;

	private Long size;

}
