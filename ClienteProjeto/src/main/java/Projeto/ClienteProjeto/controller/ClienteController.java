package Projeto.ClienteProjeto.controller;

import Projeto.ClienteProjeto.model.request.ClienteRequest;
import Projeto.ClienteProjeto.model.response.ClienteResponse;
import Projeto.ClienteProjeto.service.CadastroPratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    CadastroPratoService cadastrarPratosService;

    public ClienteController(CadastroPratoService cadastrarPratosService) {
        this.cadastrarPratosService = cadastrarPratosService;
    }

@PostMapping("/adicionarPrato")
public ClienteResponse cadastrarPratos(@RequestBody ClienteRequest clienteRequest) {
    return cadastrarPratosService.cadastrarPratos(clienteRequest);
}
}
