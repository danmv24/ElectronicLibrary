package com.example.ElectronicLibrary;

import com.example.ElectronicLibrary.property.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ElectronicLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectronicLibraryApplication.class, args);
	}

}
