package com.digitalse.cbm.back.services.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OcrHttpConnection {

    private String ATUALIZAR_OCR_URL;

    private OcrHttpConnection(@Value("${ocr-urn}") String OCR_URN){
        ATUALIZAR_OCR_URL = OCR_URN+"/ocr/queue/atualizar";

        System.out.println(ATUALIZAR_OCR_URL);
    }

    public void sendId(List<Long> ids) throws IOException {
        try {
            new RestTemplate().postForObject(ATUALIZAR_OCR_URL, ids, ResponseEntity.class);
           
            System.out.println("Ocr Connection Success");
       
        } catch (Exception e) {
            System.out.println("Ocr Connection Failed");
        }
    }
}
