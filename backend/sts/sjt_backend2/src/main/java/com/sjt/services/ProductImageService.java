package com.sjt.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sjt.payloads.ProductImageDto;

public interface ProductImageService {

	// upload multiple images
	public void uploadImage(MultipartFile[] files, Long productId) throws IOException;

	// delete productImage by productImage id and product id
	public void deleteProductImageByProductImageIdAndProductId(Long productImageId, Long productId);

	// delete list of productImage by product id
	public void deleteProductImageByProductId(Long productId);

	// download list of images by name
	public List<ProductImageDto> downloadListOfImagesByName(String name);

	// get single image by productImage id and product id
	public ProductImageDto getSingleImageByProductImageIdAndProductId(Long productImageId, Long productId);

	// get list of images by product id
	public List<ProductImageDto> getListOfImagesByProductId(Long productId);

}
