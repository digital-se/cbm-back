package com.digitalse.cbm.back.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.digitalse.cbm.back.responseFiles.RFBucket;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RabbitMQService {

    public Connection connection;

    @EventListener(ApplicationReadyEvent.class)
    public void startConnection(@Value("${rabbit.host}") String host, @Value("${rabbit.port}") int port,
            @Value("${rabbit.username}") String username, @Value("${rabbit.password}") String password)
            throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        connection = factory.newConnection();
        sendMessage();
    }

    public void sendMessage()
            throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        try (Channel channel = connection.createChannel()) {
            String message = "teste do rabbit!";
            // channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            channel.basicPublish("digital-se-cbm", "ocr_1", false, null, message.getBytes());
            // channel.basicPublish("ocr", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

    }

    public void serialize(MultipartFile image) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("image.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(image);
        out.close();
        file.close();
        System.out.println("Object has been serialized");
    }

    public RFBucket deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("rfbucket.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        RFBucket rf = (RFBucket) in.readObject();
        in.close();
        file.close();
        System.out.println("Object has been deserialized");
        return rf;
    }
}
