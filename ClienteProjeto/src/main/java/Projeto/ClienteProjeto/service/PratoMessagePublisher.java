package Projeto.ClienteProjeto.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
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

    public void enviarMensagemPratoCriado(String pratoNome) {
        String mensagem = "Novo prato cadastrado: " + pratoNome;
        rabbitTemplate.convertAndSend(exchangeName, routingKey, mensagem);
        System.out.println("Mensagem enviada: " + mensagem);
    }

    public void enviarMensagemPratoAtualizado(String pratoNome) {
        String mensagem = "Prato atualizado: " + pratoNome;
        rabbitTemplate.convertAndSend(exchangeName, routingKey, mensagem);
        System.out.println("Mensagem enviada: " + mensagem);
    }
}