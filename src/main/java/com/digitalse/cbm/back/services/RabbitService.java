package com.digitalse.cbm.back.services;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RabbitService {
    private final String exchanceName = "digital-se-cbm";
    private final String routingKey = "ocr_v1";

    private final String queueName = "ocr";

    private Connection connection;

    byte[] messageBytes;

    @Bean
    public SimplePropertyValueConnectionNameStrategy cns() {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.ocr");
    }

    @Bean
    public Connection ConnectionFactory(@Value("${rabbitmq.host}") String host, @Value("${rabbitmq.port}") int port,
            @Value("${rabbitmq.username}") String username, @Value("${rabbitmq.password}") String password,
            ConnectionNameStrategy cns) throws IOException, TimeoutException {
        ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();
        rabbitConnectionFactory.setHost(host);
        rabbitConnectionFactory.setPort(port);
        rabbitConnectionFactory.setUsername(username);
        rabbitConnectionFactory.setPassword(password);

        this.connection = rabbitConnectionFactory.newConnection();
        return this.connection;
    }

    @Bean
    Queue queue() {
        Queue queue = new Queue(queueName, true);
        queue.addArgument("x-queue-mode", "lazy");
        return queue;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchanceName);
    }

    public byte[] consumeMessage() throws IOException {
        Channel channel = connection.createChannel();
        GetResponse response = channel.basicGet(queueName, false);
        return response.getBody();
    }

    public void publishMessage(byte[] bytes) throws IOException {
        Channel channel = connection.createChannel();
        channel.basicPublish(exchanceName, routingKey, new AMQP.BasicProperties(), bytes);
    }

    /*
     * @Bean Binding binding(Queue queue, DirectExchange exchange) { return
     * BindingBuilder.bind(queue).to(exchange).with("ocr_1"); }
     */

    /*
     * @Bean public PooledChannelConnectionFactory
     * rabbitConnectionFactory(@Value("${rabbitmq.host}") String host,
     * 
     * @Value("${rabbitmq.port}") int port, @Value("${rabbitmq.username}") String
     * username,
     * 
     * @Value("${rabbitmq.password}") String password) { ConnectionFactory
     * rabbitConnectionFactory; rabbitConnectionFactory = new
     * ConnectionFactory(host, port);
     * 
     * rabbitConnectionFactory.setHost(host); rabbitConnectionFactory.setPort(port);
     * rabbitConnectionFactory.setUsername(username);
     * rabbitConnectionFactory.setPassword(password);
     * 
     * 
     * 
     * return connectionFactory; }
     */

    /*
     * @Bean SimpleMessageListenerContainer container(ConnectionFactory
     * connectionFactory, MessageListenerAdapter listenerAdapter) {
     * SimpleMessageListenerContainer container = new
     * SimpleMessageListenerContainer();
     * container.setConnectionFactory(rabbitConnectionFactory("",0,"",""));
     * container.setQueueNames(queueName);
     * container.setMessageListener(listenerAdapter); return container; }
     */

    /*
     * @Bean MessageListenerAdapter listenerAdapter(/* RabbitReceiver BucketDTO
     * receiver) { return new MessageListenerAdapter(receiver, "receiveMessage"); }
     */
}
