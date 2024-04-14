package Projeto.ClienteProjeto.service;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import Projeto.ClienteProjeto.exception.ErrorBadRequest;
import Projeto.ClienteProjeto.exception.ErrorNotFound;
import Projeto.ClienteProjeto.openFeingConsuming.Consuming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPratoService {

    @Autowired
    private Consuming consuming;

    public ClienteResponse cadastrarPratos(ClienteRequest clienteRequest) {
        if (clienteRequest.getPrato().isEmpty()|| clienteRequest.getPais().isEmpty()) {
            throw new ErrorBadRequest("Nome e preço são obrigatórios");
        }
        return consuming.cadastrarPratos(clienteRequest);
    }

    public ClienteResponse atualizarPrato(Long id, ClienteRequest clienteRequest) {
        if (clienteRequest.getPrato().isEmpty() || clienteRequest.getPais().isEmpty()) {
            throw new ErrorBadRequest("Prato e país são obrigatórios");
        }
        try {
            return consuming.atualizarPrato(id, clienteRequest);
        } catch (Exception e) {
            throw new ErrorNotFound("Id não encontrado");
        }
    }

}
