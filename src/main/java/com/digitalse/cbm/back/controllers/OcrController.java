package com.digitalse.cbm.back.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class OcrController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/documentos/{id}/arquivos/{id}/ocr")
    public ResponseEntity<MultiValueMap<String, String>> receber(@RequestParam(name = "file") MultipartFile file) throws RestClientException, IOException {
        try {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/documentos/{id}/arquivos/{id}/ocr")
    public ResponseEntity<MultiValueMap<String, String>> reenviar(@RequestParam(name = "file") MultipartFile file) throws RestClientException, IOException {
        try {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
