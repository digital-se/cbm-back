package com.digitalse.cbm.back.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.digitalse.cbm.back.responseFiles.RFBucket;
import com.digitalse.cbm.back.responseFiles.RFBucketRabbitMQ;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class RabbitMQService {

    

    /* public void sendImage()
            throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        try (Channel channel = connection.createChannel()) {

            String message = serialize(new RFBucketRabbitMQ(0L, "mime", 0L, "dados".getBytes()));

            //String message = "teste do rabbit!";
            // channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            channel.basicPublish("digital-se-cbm", "ocr_1", false, null, message.getBytes());
            // channel.basicPublish("ocr", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

    }

    public String serialize(RFBucketRabbitMQ image) throws FileNotFoundException, IOException {
        String serialized = new ObjectMapper().writeValueAsString(image);
        //System.out.println(serialized);
        return serialized;
    }

    public RFBucket deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("rfbucket.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        RFBucket rf = (RFBucket) in.readObject();
        in.close();
        file.close();
        System.out.println("Object has been deserialized");
        return rf;
    } */
}
