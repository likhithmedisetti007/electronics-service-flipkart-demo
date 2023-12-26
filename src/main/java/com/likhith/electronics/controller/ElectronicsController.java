package com.likhith.electronics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.electronics.dto.ElectronicsResponse;
import com.likhith.electronics.dto.Product;
import com.likhith.electronics.exception.ErrorMessage;
import com.likhith.electronics.service.ElectronicsService;

@RestController
@RequestMapping("/electronics")
public class ElectronicsController {

	@Autowired
	ElectronicsService electronicsService;

	@GetMapping("/{subCategoryName}/getAllProducts")
	public ResponseEntity<ElectronicsResponse> getAllProducts(@PathVariable("subCategoryName") String subCategoryName,
			@RequestParam(name = "availability") boolean availability, @RequestParam(name = "minPrice") double minPrice,
			@RequestParam(name = "maxPrice") double maxPrice) {

		List<Product> products = electronicsService.getAllProducts(subCategoryName, availability, minPrice, maxPrice);

		if (!CollectionUtils.isEmpty(products)) {
			return ResponseEntity.ok().body(new ElectronicsResponse(subCategoryName, products));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
					.body(new ElectronicsResponse(new ErrorMessage(HttpStatus.NOT_FOUND.value(),
							"No products found for the subCategory: " + subCategoryName)));
		}
	}

	@PostMapping("/{subCategoryName}/addProduct")
	public ResponseEntity<String> addProduct(@PathVariable("subCategoryName") String subCategoryName,
			@RequestBody Product product) {

		String message = electronicsService.addProduct(subCategoryName, product);
		return ResponseEntity.ok().body(message);
	}

	@PutMapping("/{subCategoryName}/updateProduct")
	public ResponseEntity<String> updateProduct(@PathVariable("subCategoryName") String subCategoryName,
			@RequestBody Product product) {

		String message = electronicsService.updateProduct(subCategoryName, product);
		return ResponseEntity.ok().body(message);
	}

	@DeleteMapping("/{subCategoryName}/deleteProduct")
	public ResponseEntity<String> deleteProduct(@PathVariable("subCategoryName") String subCategoryName,
			@RequestBody Product product) {

		String message = electronicsService.deleteProduct(subCategoryName, product);
		return ResponseEntity.ok().body(message);
	}

}