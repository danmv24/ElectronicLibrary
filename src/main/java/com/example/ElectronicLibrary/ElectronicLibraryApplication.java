package com.example.ElectronicLibrary;

import com.example.ElectronicLibrary.property.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
public class ElectronicLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicLibraryApplication.class, args);
	}

}
