package com.digitalse.cbm.back.services;

import java.io.IOException;

import com.digitalse.cbm.back.rto.RTOBucket;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqService {

    @Autowired
    private RabbitTemplate rt;

    public void sendImage(String nomeFila, RTOBucket rtoBucket) throws AmqpException, IOException {
        rt.convertAndSend(nomeFila, rtoBucket);
    }

    //@RabbitListener(queues = "ocr")
    public void listener(RTOBucket bucket){
        System.out.println(">>> Imagem recebida do ocr: "+bucket.getArquivo_id());
    }

}
