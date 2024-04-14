package Projeto.ClienteProjeto.controller;

import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import Projeto.ClienteProjeto.exception.ErrorBadRequest;
import Projeto.ClienteProjeto.service.CadastroPratoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private CadastroPratoService cadastrarPratosService;

    private ClienteRequest clienteRequest;

    private ClienteResponse clienteResponse;


    @Test
    void cadastrarPratos() {
        clienteRequest = new ClienteRequest();
        clienteRequest.setPrato("Feijoada");
        clienteRequest.setPais("Brasil");

        clienteResponse = new ClienteResponse();
        clienteResponse.setId(1L);
        clienteResponse.setPrato("Feijoada");
        clienteResponse.setPais("Brasil");

        Mockito.when(cadastrarPratosService.cadastrarPratos(clienteRequest)).thenReturn(clienteResponse);
        ClienteResponse result = clienteController.cadastrarPratos(clienteRequest);
        assertEquals(clienteResponse, result);
        assertEquals(clienteResponse.getPrato(), result.getPrato());
        assertEquals(clienteResponse.getPais(), result.getPais());
        assertEquals(clienteResponse, result);



    }

    @Test
    void atualizarPrato() {
        clienteRequest = new ClienteRequest();
        clienteRequest.setPrato("Feijoada");
        clienteRequest.setPais("Brasil");

        clienteResponse = new ClienteResponse();
        clienteResponse.setId(1L);
        clienteResponse.setPrato("Feijoada");
        clienteResponse.setPais("Brasil");

        Mockito.when(cadastrarPratosService.atualizarPrato(1L, clienteRequest)).thenReturn(clienteResponse);
        ClienteResponse result = clienteController.atualizarPrato(1L, clienteRequest);
        assertEquals(clienteResponse, result);
        assertEquals(clienteResponse.getId(), result.getId());
        assertEquals(clienteResponse.getPrato(), result.getPrato());
        assertEquals(clienteResponse.getPais(), result.getPais());
        assertEquals(clienteResponse, result);
    }

}