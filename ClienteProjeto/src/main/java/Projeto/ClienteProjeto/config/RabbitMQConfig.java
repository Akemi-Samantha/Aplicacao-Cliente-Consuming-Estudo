package Projeto.ClienteProjeto.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.exchange.name}")
    private String exchangeName;

    @Value("${rabbit.queue.name.cadastro}")
    private String queueNameCadastro;

    @Value("${rabbit.routing.key.cadastro}")
    private String routingKeyCadastro;

    @Value("${rabbit.queue.name.atualizacao}")
    private String queueNameAtualizacao;

    @Value("${rabbit.routing.key.atualizacao}")
    private String routingKeyAtualizacao;

    @Value("${rabbit.queue.name.deletar}")
    private String queueNameDeletar;

    @Value("${rabbit.routing.key.deletar}")
    private String routingKeyDeletar;

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Queue queueCadastro() {
        return new Queue(queueNameCadastro, true);
    }

    @Bean
    Binding binding(Queue queueCadastro, TopicExchange exchange) {
        return BindingBuilder.bind(queueCadastro).to(exchange).with(routingKeyCadastro);
    }

    @Bean
    Queue queueAtualizacao() {
        return new Queue(queueNameAtualizacao, true);
    }

    @Bean
    Binding bindingAtualizacao(Queue queueAtualizacao, TopicExchange exchange) {
        return BindingBuilder.bind(queueAtualizacao).to(exchange).with(routingKeyAtualizacao);
    }

    @Bean
    Queue queueDeletar() {
        return new Queue(queueNameDeletar, true);
    }

    @Bean
    Binding bindingDeletar(Queue queueDeletar, TopicExchange exchange) {
        return BindingBuilder.bind(queueDeletar).to(exchange).with(routingKeyDeletar);
    }
}