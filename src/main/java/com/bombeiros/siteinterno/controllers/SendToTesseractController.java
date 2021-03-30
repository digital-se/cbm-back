package com.bombeiros.siteinterno.controllers;

import java.io.IOException;

import com.bombeiros.siteinterno.services.SendToTesseractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ocrService")
public class SendToTesseractController {

    @Autowired
    private SendToTesseractService tessService;

    // broken
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/sendImage")
    public MultiValueMap<String, String> getFromUrl(@RequestParam(name = "file") MultipartFile file) throws RestClientException, IOException {

        return tessService.send(file);
    }

}