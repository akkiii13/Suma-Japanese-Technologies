package com.sjt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjt.entities.entityHelper.ProductImagePrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "productImage")
public class ProductImage {

	@EmbeddedId
	private ProductImagePrimaryKey productImageId;

	private String name;

	private String type;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] imageData;

	@ManyToOne
	@MapsId("productIdPK")
	@JoinColumn(name = "productId", nullable = true)
	@JsonIgnore
	private Products products;

}
