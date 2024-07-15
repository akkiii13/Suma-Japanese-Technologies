package com.sjt.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sjt.payloads.ProductImageDto;
import com.sjt.responseObjects.ApiResponse;
import com.sjt.responseObjects.ProductImageResponse;
import com.sjt.services.ProductImageService;

@RestController
@RequestMapping("/productImage")
@CrossOrigin("http://localhost:3000")
@Validated
public class ProductImageController {

	@Autowired
	ProductImageService productImageService;

	public ProductImageController(ProductImageService productImageService) {
		super();
	}

	// upload multiple images
	@PostMapping("/save/{productId}")
	public ResponseEntity<ApiResponse> saveImage(@RequestParam("multipleFiles") MultipartFile[] files,
			@PathVariable(name = "productId") Long productId) throws IOException {
		this.productImageService.uploadImage(files, productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Images uploaded successfully", true), HttpStatus.OK);
	}

	// delete productImage by productImage id and product id
	@DeleteMapping("/delete/deleteProductImageByProductImageIdAndProductId/productImageId/{productImageId}/productId/{productId}")
	public ResponseEntity<ApiResponse> deleteProductImageByProductImageIdAndProductId(
			@PathVariable("productImageId") Long productImageId, @PathVariable("productId") Long productId) {
		this.productImageService.deleteProductImageByProductImageIdAndProductId(productImageId, productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted successfully", true), HttpStatus.OK);
	}

	// delete list of productImage by product id
	@DeleteMapping("/delete/deleteProductImageByProductId/{productId}")
	public ResponseEntity<ApiResponse> deleteProductImageByProductId(@PathVariable("productId") Long productId) {
		this.productImageService.deleteProductImageByProductId(productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("List of products deleted successfully", true),
				HttpStatus.OK);
	}

	// download list of images by name
	@GetMapping("/downloadListOfImagesByName/{fileName}")
	public ResponseEntity<List<ProductImageResponse>> downloadListOfImagesByName(@PathVariable String fileName) {
		List<ProductImageResponse> downloadListOfImagesByName = productImageService.downloadListOfImagesByName(fileName)
				.stream().map(i -> {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
							.path(String.valueOf(i.getProductImageId().getProductImageIdPK())).toUriString();
					return new ProductImageResponse(i.getName(), fileDownloadUri, i.getType(),
							(long) i.getImageData().length);
				}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(downloadListOfImagesByName);
	}

	// get single image by productImage id and product id
	@GetMapping("/getSingleImageByProductImageIdAndProductId/productImageId/{productImageId}/productId/{productId}")
	public ResponseEntity<byte[]> getSingleImageByProductImageIdAndProductId(
			@PathVariable("productImageId") Long productImageId, @PathVariable("productId") Long productId) {
		ProductImageDto singleImageByProductImageIdAndProductId = this.productImageService
				.getSingleImageByProductImageIdAndProductId(productImageId, productId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + singleImageByProductImageIdAndProductId.getName() + "\"")
				.body(singleImageByProductImageIdAndProductId.getImageData());
	}

	// get list of images by product id
	@GetMapping("/getListOfImagesByProductId/{productId}")
	public ResponseEntity<List<ProductImageResponse>> getListOfImagesByProductId(
			@PathVariable("productId") Long productId) {
		List<ProductImageResponse> listOfImagesByProductId = productImageService.getListOfImagesByProductId(productId)
				.stream().map(i -> {
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
							.path(String.valueOf(i.getProductImageId().getProductImageIdPK())).toUriString();
					return new ProductImageResponse(i.getName(), fileDownloadUri, i.getType(),
							(long) i.getImageData().length);
				}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listOfImagesByProductId);
	}

}
