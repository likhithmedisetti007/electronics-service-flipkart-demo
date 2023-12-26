package com.likhith.electronics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.electronics.document.Laptop;
import com.likhith.electronics.document.Mobile;
import com.likhith.electronics.dto.Product;
import com.likhith.electronics.exception.ValidationException;
import com.likhith.electronics.mapper.ElectronicsMapper;
import com.likhith.electronics.repository.LaptopRepository;
import com.likhith.electronics.repository.MobileRepository;

@Component
public class ElectronicsServiceImpl implements ElectronicsService {

	@Autowired
	LaptopRepository laptopRepository;

	@Autowired
	MobileRepository mobileRepository;

	@Autowired
	ElectronicsMapper electronicsMapper;

	@Override
	public List<Product> getAllProducts(String subCategoryName, boolean availability, double minPrice,
			double maxPrice) {

		List<Product> productList = new ArrayList<>();

		switch (subCategoryName.toLowerCase()) {
		case "laptop":
			List<Laptop> laptopList = laptopRepository.findByQuery(availability ? 0 : -1, minPrice, maxPrice);

			if (CollectionUtils.isEmpty(laptopList)) {
				throw new ValidationException(HttpStatus.NOT_FOUND.value(), "No products found");
			}

			productList = electronicsMapper.mapToProductListFromLaptopList(laptopList);
			break;
		case "mobile":
			List<Mobile> mobileList = mobileRepository.findByQuery(availability ? 0 : -1, minPrice, maxPrice);

			if (CollectionUtils.isEmpty(mobileList)) {
				throw new ValidationException(HttpStatus.NOT_FOUND.value(), "No products found");
			}

			productList = electronicsMapper.mapToProductListFromMobileList(mobileList);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}
		return productList;
	}

	@Override
	public String addProduct(String subCategoryName, Product product) {

		String message = null;

		switch (subCategoryName.toLowerCase()) {
		case "laptop":

			Laptop laptop = electronicsMapper.mapToLaptopFromProduct(product);

			laptopRepository
					.findByNameAndBrandAndAttributes(laptop.getName(), laptop.getBrand(), laptop.getAttributes())
					.ifPresent(t -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product already available");
					});

			laptopRepository.save(laptop);

			message = "Product added successfully";
			break;
		case "mobile":

			Mobile mobile = electronicsMapper.mapToMobileFromProduct(product);

			mobileRepository
					.findByNameAndBrandAndAttributes(mobile.getName(), mobile.getBrand(), mobile.getAttributes())
					.ifPresent(t -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product already available");
					});

			mobileRepository.save(mobile);

			message = "Product added successfully";
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		return message;
	}

	@Override
	public String updateProduct(String subCategoryName, Product product) {

		String message = null;

		switch (subCategoryName.toLowerCase()) {
		case "laptop":

			Laptop laptop = electronicsMapper.mapToLaptopFromProduct(product);

			Laptop laptopFromDB = laptopRepository
					.findByNameAndBrandAndAttributes(laptop.getName(), laptop.getBrand(), laptop.getAttributes())
					.orElseThrow(() -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product not available");
					});

			laptop.setId(laptopFromDB.getId());

			laptopRepository.save(laptop);

			message = "Product updated successfully";
			break;
		case "mobile":

			Mobile mobile = electronicsMapper.mapToMobileFromProduct(product);

			Mobile mobileFromDB = mobileRepository
					.findByNameAndBrandAndAttributes(mobile.getName(), mobile.getBrand(), mobile.getAttributes())
					.orElseThrow(() -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product not available");
					});

			mobile.setId(mobileFromDB.getId());

			mobileRepository.save(mobile);

			message = "Product updated successfully";
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		return message;
	}

	@Override
	public String deleteProduct(String subCategoryName, Product product) {

		String message = null;

		switch (subCategoryName.toLowerCase()) {
		case "laptop":

			Laptop laptop = electronicsMapper.mapToLaptopFromProduct(product);

			Laptop laptopFromDB = laptopRepository
					.findByNameAndBrandAndAttributes(laptop.getName(), laptop.getBrand(), laptop.getAttributes())
					.orElseThrow(() -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product not available");
					});

			laptopRepository.delete(laptopFromDB);

			message = "Product deleted successfully";
			break;
		case "mobile":

			Mobile mobile = electronicsMapper.mapToMobileFromProduct(product);

			Mobile mobileFromDB = mobileRepository
					.findByNameAndBrandAndAttributes(mobile.getName(), mobile.getBrand(), mobile.getAttributes())
					.orElseThrow(() -> {
						throw new ValidationException(HttpStatus.CONFLICT.value(), "Product not available");
					});

			mobileRepository.delete(mobileFromDB);

			message = "Product deleted successfully";
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		return message;
	}

}