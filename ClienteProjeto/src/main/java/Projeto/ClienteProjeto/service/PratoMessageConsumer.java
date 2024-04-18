package Projeto.ClienteProjeto.service;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PratoMessageConsumer {

    @Autowired
    private CadastroPratoService cadastroPratoService;

    @RabbitListener(queues = "${rabbit.queue.name}")
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
        if (mensagem.startsWith("Novo prato cadastrado: ")) {
            processarNovoPrato(mensagem.replace("Novo prato cadastrado: ", ""));
        } else if (mensagem.startsWith("Prato atualizado: ")) {
            String[] partes = mensagem.replace("Prato atualizado: ", "").split(";");
            Long id = Long.parseLong(partes[0]);
            processarAtualizacaoPrato(partes[1], id);
        }
    }

    private void processarNovoPrato(String pratoNome) {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setPrato(pratoNome);
        cadastroPratoService.cadastrarPratos(clienteRequest);
    }

    private void processarAtualizacaoPrato(String pratoNome, Long id) {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setPrato(pratoNome);
        cadastroPratoService.atualizarPrato(id, clienteRequest);
    }


}