package com.digitalse.cbm.back.rabbitmq;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConnection {
    private final String NOME_EXCHANGE = "digital-se-cbm";
    private final String NOME_QUEUE_OCR = "ocr_queue";
    private final String NOME_QUEUE_PREPROCESSOR = "preprocessor_queue";
    //private final String NOME_KEY_PREPROCESSOR = "pp_key";
    
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

    //TODO: Verificar implementação sem AmqpAdmin
    @PostConstruct
    private void iniciar(){
        try{
            Queue queuePreprocessor = this.queue(NOME_QUEUE_PREPROCESSOR);
            Queue queueOcr = this.queue(NOME_QUEUE_OCR);

            DirectExchange exchange = this.exchange(NOME_EXCHANGE);

            Binding bindingPreprocessor = this.binding(queuePreprocessor, exchange);
            Binding bindingOcr = this.binding(queueOcr, exchange);
    

            this.amqpAdmin.declareQueue(queuePreprocessor);
            this.amqpAdmin.declareQueue(queueOcr);

            this.amqpAdmin.declareExchange(exchange);

            this.amqpAdmin.declareBinding(bindingPreprocessor);
            this.amqpAdmin.declareBinding(bindingOcr);
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }

}
