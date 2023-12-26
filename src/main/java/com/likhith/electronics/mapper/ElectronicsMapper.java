package com.likhith.electronics.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.likhith.electronics.document.Laptop;
import com.likhith.electronics.document.Mobile;
import com.likhith.electronics.dto.Product;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ElectronicsMapper {

	List<Product> mapToProductListFromLaptopList(List<Laptop> laptopList);

	Laptop mapToLaptopFromProduct(Product product);

	List<Product> mapToProductListFromMobileList(List<Mobile> mobileList);

	Mobile mapToMobileFromProduct(Product product);

}