package com.digitalse.cbm.back.rabbitmq;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConnection {
    public static final String NOME_EXCHANGE = "digital-se-cbm";
    public static final String NOME_QUEUE_OCR = "ocr";
    public static final String NOME_QUEUE_PREPROCESSOR = "preprocessor";
    public static final String NOME_KEY_PREPROCESSOR = "pp_key";
    
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    public SimplePropertyValueConnectionNameStrategy cns() {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.ocr");
    }

    private Queue queue(String nome_queue){
        return new Queue(nome_queue, true, false, false);
    }
    
    private DirectExchange exchange(String nome_exchange){
        return new DirectExchange(nome_exchange, true, false);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void iniciar(){
        try{
            Queue queueOcr = this.queue(NOME_QUEUE_OCR);

            DirectExchange exchange = this.exchange(NOME_EXCHANGE);
    
            Binding bindingOcr = this.binding(queueOcr, exchange);
    
            this.amqpAdmin.declareQueue(queueOcr);
            this.amqpAdmin.declareExchange(exchange);
            this.amqpAdmin.declareBinding(bindingOcr);
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        
    }

    /* public byte[] consumeMessage() throws IOException {
        Channel channel = connection.createChannel();
        GetResponse response = channel.basicGet(NOME_QUEUE_PREPROCESSOR, false);
        return response.getBody();
    }

    public void publishMessage(byte[] bytes) throws IOException {
        Channel channel = Connection.createChannel();
        channel.basicPublish(NOME_EXCHANGE, NOME_KEY, new AMQP.BasicProperties(), bytes);
    } */

}
