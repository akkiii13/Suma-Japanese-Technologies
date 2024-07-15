package com.sjt.payloads;

import com.sjt.entities.Products;
import com.sjt.entities.entityHelper.ProductImagePrimaryKey;

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

	private transient Products products;

}
