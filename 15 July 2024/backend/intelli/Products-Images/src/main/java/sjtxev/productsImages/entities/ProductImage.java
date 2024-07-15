package sjtxev.productsImages.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sjtxev.productsImages.entities.entityHelper.ProductImagePrimaryKey;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

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

	private Long productId;

}
