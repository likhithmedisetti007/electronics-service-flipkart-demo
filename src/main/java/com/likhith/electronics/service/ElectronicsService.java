package com.likhith.electronics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.electronics.dto.Product;

@Service
public interface ElectronicsService {

	List<Product> getAllProducts(String subCategoryName, boolean availability, double minPrice, double maxPrice);

	String addProduct(String subCategoryName, Product product);

	String updateProduct(String subCategoryName, Product product);

	String deleteProduct(String subCategoryName, Product product);

}