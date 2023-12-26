package com.likhith.electronics.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.electronics.dto.Attribute;
import com.likhith.electronics.dto.Inventory;
import com.likhith.electronics.dto.Price;

import lombok.Data;

@Document(collection = "mobile")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mobile {

	@Id
	@JsonIgnore
	private String id;
	private String name;
	private String brand;
	private String description;
	private Price price;
	private Inventory inventory;
	private List<Attribute> attributes;

}