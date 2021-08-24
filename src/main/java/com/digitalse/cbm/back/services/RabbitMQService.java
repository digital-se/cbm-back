package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RabbitMQService {

    public Connection connection;
    
    @EventListener(ApplicationReadyEvent.class)
    public void startConnection() throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(3309);
        factory.setUsername("back");
        factory.setPassword("cbm");
        connection = factory.newConnection();
		sendMessage();
    }

    public void sendMessage() throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        try (Channel channel = connection.createChannel()) {
            String message = "Hello World!";
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            channel.basicPublish("digital-se-cbm", "ocr_1", false, null, "teste do rabbit".getBytes());
            //channel.basicPublish("ocr", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

    }
}
