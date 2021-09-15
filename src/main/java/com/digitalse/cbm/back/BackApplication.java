package com.digitalse.cbm.back;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.digitalse.cbm.back.services.OcrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BackApplication {

	@Autowired
	private OcrService ocrService;

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@PostConstruct
	public void updateOcr() throws IOException{
		ocrService.updateOcr();
	}

}
