package Projeto.ClienteProjeto.openFeingConsuming;

import Projeto.ClienteProjeto.model.request.ClienteRequest;
import Projeto.ClienteProjeto.model.response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Pratos", url = "http://localhost:8081/prato")
public interface Consuming  {

    @PostMapping("/adicionarPrato")
    ClienteResponse cadastrarPratos(ClienteRequest clienteRequest);
}
