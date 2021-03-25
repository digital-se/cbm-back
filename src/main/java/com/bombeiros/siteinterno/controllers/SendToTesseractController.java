package com.bombeiros.siteinterno.controllers;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;

import com.bombeiros.siteinterno.message.TesteResponseFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/testes")
public class SendToTesseractController {

    // broken
    @PostMapping("/getFromUrl")
    public ResponseEntity<String> getFromUrl(@RequestParam(name = "file") MultipartFile file)
            throws RestClientException, IOException {

        // converte arquivo para formato enviavel
        ByteArrayResource fileConvertido = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // seta o media type para o correto (opcional, não faz diferença na pratica)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // define um tipo de "json" para enviar os dados
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileConvertido);

        // cria entidade de resposta (opcional, não faz diferença na pratica)
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // envia um post para o serviço do ocr e recebe a resposta
        RestTemplate restTemplate = new RestTemplate();
        
        // retorna a resposta do ocr (precisa mudar pro formato la de
        // ResponseEntity.ok(resultado);
        return restTemplate.postForEntity("http://localhost:9191/ocr/extrair", requestEntity, String.class);


    }

}
