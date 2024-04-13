package Projeto.ClienteProjeto.service;

import Projeto.ClienteProjeto.model.request.ClienteRequest;
import Projeto.ClienteProjeto.model.response.ClienteResponse;
import Projeto.ClienteProjeto.openFeingConsuming.Consuming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPratoService {

    @Autowired
    private Consuming consuming;

    public ClienteResponse cadastrarPratos(ClienteRequest clienteRequest) {
        return consuming.cadastrarPratos(clienteRequest);
    }
}
