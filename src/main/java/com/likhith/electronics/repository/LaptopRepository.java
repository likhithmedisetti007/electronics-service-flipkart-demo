package com.likhith.electronics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.likhith.electronics.document.Laptop;
import com.likhith.electronics.dto.Attribute;

public interface LaptopRepository extends MongoRepository<Laptop, String> {

	@Query("{$and:[{'inventory.available': {$gt: ?0}}, {'price.amount': {$gte: ?1, $lte: ?2}}]}")
	List<Laptop> findByQuery(int availableCount, double minPrice, double maxPrice);

	Optional<Laptop> findByNameAndBrandAndAttributes(String name, String brand, List<Attribute> attributeList);

}