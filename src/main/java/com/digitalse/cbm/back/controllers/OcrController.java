package com.digitalse.cbm.back.controllers;

import java.io.IOException;

import com.digitalse.cbm.back.DTO.BucketDTO;
import com.digitalse.cbm.back.rabbitmq.RabbitMQConnection;
import com.digitalse.cbm.back.services.OcrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
public class OcrController {

    @Autowired
    public OcrService ocr = new OcrService();

    @PostMapping("/ocr/testes")
    public ResponseEntity<InputStreamResource> enviarImagem(@RequestParam(name = "file") MultipartFile file) throws RestClientException, IOException {
        try {
            //RFOcrBucket ocrbucket =  ocr.sendAmqp(file);
            ocr.sendImage(RabbitMQConnection.NOME_QUEUE_OCR, new BucketDTO(0L, file.getOriginalFilename(), file.getContentType(),
            file.getSize(), file.getBytes(), null, null));
            return ResponseEntity.status(HttpStatus.OK).build();
            /* return ResponseEntity.status(HttpStatus.OK).contentLength(ocrbucket.getTamanho())
                    .contentType(org.springframework.http.MediaType.parseMediaType(ocrbucket.getMime()))
                    .body(new InputStreamResource(new ByteArrayInputStream(ocrbucket.getDados()))); */
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/documentos/{id}/arquivos/{id}/ocr")
    public ResponseEntity<MultiValueMap<String, String>> receber(@RequestParam(name = "file") MultipartFile file) throws RestClientException, IOException {
        try {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

}
