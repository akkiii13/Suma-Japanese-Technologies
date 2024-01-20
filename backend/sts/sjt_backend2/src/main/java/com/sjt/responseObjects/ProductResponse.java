package com.sjt.responseObjects;

import java.util.List;

import com.sjt.payloads.ProductsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ProductResponse {

	private List<ProductsDto> productsDto;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private Boolean lastPage;

}
