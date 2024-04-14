package Projeto.ClienteProjeto.controller;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import Projeto.ClienteProjeto.service.CadastroPratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/atualizarPrato/{id}")
        public ClienteResponse atualizarPrato(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        return cadastrarPratosService.atualizarPrato(id, clienteRequest);
    }

}
