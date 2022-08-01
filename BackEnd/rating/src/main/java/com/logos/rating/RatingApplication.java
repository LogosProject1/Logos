package com.logos.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingApplication.class, args);
	}

}
