package com.likhith.electronics.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.electronics.exception.ErrorMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElectronicsResponse {

	private String name;
	private List<Product> products;

	private ErrorMessage message;

	public ElectronicsResponse(String name, List<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public ElectronicsResponse(ErrorMessage message) {
		super();
		this.message = message;
	}

}