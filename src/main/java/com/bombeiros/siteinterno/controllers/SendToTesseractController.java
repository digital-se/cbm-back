package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;

import com.bombeiros.siteinterno.message.TesteResponseFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/testes")
public class SendToTesseractController {

    @PostMapping("/teste")
    public /*ResponseEntity<String>*/ String teste(MultipartFile file) throws JsonMappingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        // ObjectMapper mapper = new ObjectMapper();
        
        TesteResponseFile coiso = new TesteResponseFile(file);

        /*ResponseEntity<String>*/ String response = restTemplate.postForObject("http://localhost:9001/ocr/extrair", coiso,
                String.class);

        // ResponseEntity<String> response =
        // restTemplate.getForEntity("https://randomuser.me/api/", String.class);
        // ObjectMapper mapper = new ObjectMapper();
        // JsonNode root = mapper.readTree(response.getBody());

        return response;

        // return root;
    }

    // @GetMapping("/teste2")
    // public ResponseBody teste2(MultipartFile file) {
    //     // create a client
    //     var client = HttpClient.newHttpClient();

    //     // create a request
    //     var request = HttpRequest.newBuilder(URI.create("http://localhost:9191/ocr/extrair"))
    //             .header("accept", "application/json").build();

    //     var response = client.send(request, new JsonBodyHandler<>(APOD.class));

    //     return response.body().get().title;
    // }

}
