package Projeto.ClienteProjeto.service;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import Projeto.ClienteProjeto.exception.ErrorBadRequest;
import Projeto.ClienteProjeto.exception.ErrorNotFound;
import Projeto.ClienteProjeto.openFeingConsuming.Consuming;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPratoService {

    @Autowired
    private Consuming consuming;

    @Autowired
    private PratoMessagePublisher pratoMessagePublisher;



    public ClienteResponse cadastrarPratos(ClienteRequest clienteRequest) throws JsonProcessingException {
        if (clienteRequest.getPrato().isEmpty()|| clienteRequest.getPais().isEmpty()) {
            throw new ErrorBadRequest("Nome e preço são obrigatórios");
        }
        ClienteResponse response = consuming.cadastrarPratos(clienteRequest);
        pratoMessagePublisher.publicherMensagemPratoCriado(clienteRequest);
        return response;

    }

    public ClienteResponse atualizarPrato(Long id, ClienteRequest clienteRequest) {
        if (clienteRequest.getPrato().isEmpty() || clienteRequest.getPais().isEmpty()) {
            throw new ErrorBadRequest("Prato e país são obrigatórios");
        }
        try {
            ClienteResponse response = consuming.atualizarPrato(id, clienteRequest);
            pratoMessagePublisher.publicherMensagemPratoAtualizado(clienteRequest);
            return response;
        } catch (Exception e) {
            throw new ErrorNotFound("Id não encontrado");
        }
    }

    public void deletarPrato(Long id) {
        try {
            pratoMessagePublisher.publicherMensagemPratoDeletado();
            consuming.deletarPrato(id);
        } catch (Exception e) {
            throw new ErrorNotFound("Id não encontrado");
        }

    }
}
