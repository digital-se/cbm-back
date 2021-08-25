package com.digitalse.cbm.back.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

import com.digitalse.cbm.back.responseFiles.RFBucket;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Component
@Service
public class RabbitMQService {

    public final Connection connection;

    public RabbitMQService(@Value("${rabbitmq.host}") String host, @Value("${rabbitmq.port}") int port,
            @Value("${rabbitmq.username}") String username, @Value("${rabbitmq.password}") String password)
            throws IOException, TimeoutException {
        System.out.println("======== " + host + "======== ");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        this.connection = factory.newConnection();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startConnection() {
        //System.out.println("capivara");
    }

    /* public void sendMessage()
            throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        try (Channel channel = connection.createChannel()) {
            new RabbitMQService(host, port, username, password);
            String message = "teste do rabbit!";
            // channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            channel.basicPublish("digital-se-cbm", "ocr_1", false, null, message.getBytes());
            // channel.basicPublish("ocr", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

    } */

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
