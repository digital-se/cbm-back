package com.digitalse.cbm.back.controllers;

import java.io.IOException;

import com.digitalse.cbm.back.services.OcrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private OcrService tessService;

    public OcrController(OcrService tessService) {
        this.tessService = tessService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/v1/sendImage")
    public ResponseEntity<MultiValueMap<String, String>> getFromUrl(@RequestParam(name = "file") MultipartFile file) throws RestClientException, IOException {
        
        return  ResponseEntity.status(HttpStatus.OK).body(tessService.send(file));
    }

}
