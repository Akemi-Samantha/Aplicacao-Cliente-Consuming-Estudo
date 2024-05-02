package Projeto.ClienteProjeto.openFeingConsuming;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Pratos", url = "${url.pratos}")
public interface Consuming  {

    @PostMapping("/adicionarPrato")
    ClienteResponse cadastrarPratos(ClienteRequest clienteRequest);

    @PutMapping("/atualizarPrato/{id}")
    ClienteResponse atualizarPrato(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest);




}
