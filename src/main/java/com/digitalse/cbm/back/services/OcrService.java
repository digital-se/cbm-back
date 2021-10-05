package com.digitalse.cbm.back.services;

import java.io.IOException;

import com.digitalse.cbm.back.DTO.DTOsBucket.BucketDTO;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OcrService {

    @Autowired
    private RabbitTemplate rt;

    // Rabbit
    public void sendImage(String nomeFila, BucketDTO file) throws AmqpException, IOException {
        rt.convertAndSend(nomeFila, file);
    }

}
