package com.digitalse.cbm.back.config;

import com.digitalse.cbm.back.DTO.BucketDTO;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    static final String topicExchangeName = "digital-se-cbm";

    static final String queueName = "ocr";

    @Bean
    Queue queue() {
        Queue queue = new Queue(queueName, true);
        queue.addArgument("x-queue-mode", "lazy");
        return queue;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ocr_1");
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory(@Value("${rabbitmq.host}") String host,
            @Value("${rabbitmq.port}") int port, @Value("${rabbitmq.username}") String username,
            @Value("${rabbitmq.password}") String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory("",0,"",""));
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(/* RabbitReceiver */BucketDTO receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
