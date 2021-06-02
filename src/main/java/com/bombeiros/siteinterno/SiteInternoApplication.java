package com.bombeiros.siteinterno;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiteInternoApplication {

	// @Value("${spring.datasource.url}")
    // String url;
	
	public static void main(String[] args) {
		SpringApplication.run(SiteInternoApplication.class, args);
	}

}
