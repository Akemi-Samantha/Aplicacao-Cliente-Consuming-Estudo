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
    private final String routingKeyCadastro;
    private final String routingKeyAtualizacao;
    private final String routingKeyDeletar;

    @Autowired
    public PratoMessagePublisher(RabbitTemplate rabbitTemplate,
                                 @Value("${rabbit.exchange.name}") String exchangeName,
                                 @Value("${rabbit.routing.key.cadastro}") String routingKeyCadastro,
                                 @Value("${rabbit.routing.key.atualizacao}") String routingKeyAtualizacao,
                                 @Value("${rabbit.routing.key.deletar}") String routingKeyDeletar){
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyCadastro = routingKeyCadastro;
        this.routingKeyAtualizacao = routingKeyAtualizacao;
        this.routingKeyDeletar = routingKeyDeletar;
    }

    public void publicherMensagemPratoCriado( ClienteRequest clienteRequest) throws JsonProcessingException {
            String json = convertToJson(clienteRequest);
            rabbitTemplate.convertAndSend(exchangeName, routingKeyCadastro, json);
            System.out.println("Mensagem enviada para o RabbitMQ - Prato Criado " + json);

    }

    public void publicherMensagemPratoAtualizado( ClienteRequest clienteRequest) throws JsonProcessingException {
        String json = convertToJson(clienteRequest);
        rabbitTemplate.convertAndSend(exchangeName, routingKeyAtualizacao, json);
        System.out.println("Mensagem enviada para o RabbitMQ - Prato Atualizado" + json);

    }

    public void publicherMensagemPratoDeletado() {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyDeletar);
        System.out.println("Mensagem enviada para o RabbitMQ - Prato Deletado");

    }

    private String convertToJson(ClienteRequest clienteRequest) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(clienteRequest);
        return json;
    }
}