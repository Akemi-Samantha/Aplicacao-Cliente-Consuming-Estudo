package Projeto.ClienteProjeto.service;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PratoMessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    @Autowired
    public PratoMessagePublisher(RabbitTemplate rabbitTemplate,
                                 @Value("${rabbit.exchange.name}") String exchangeName,
                                 @Value("${rabbit.routing.key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void publicherMensagemPratoCriado( ClienteRequest clienteRequest) throws JsonProcessingException {
            String json = convertToJson(clienteRequest);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, json);
            System.out.println("Mensagem enviada para o RabbitMQ " + json);

    }

    private String convertToJson(ClienteRequest clienteRequest) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(clienteRequest);
        return json;
    }
}