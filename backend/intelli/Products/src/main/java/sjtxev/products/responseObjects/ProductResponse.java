package sjtxev.products.responseObjects;

import java.util.List;

import sjtxev.products.payloads.ProductDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ProductResponse {

	private List<ProductDto> productsDto;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;

}
