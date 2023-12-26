package com.likhith.electronics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ElectronicsServiceFlipkartDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicsServiceFlipkartDemoApplication.class, args);
	}

}