package Projeto.ClienteProjeto.service;

import Projeto.ClienteProjeto.controller.ClienteController;
import Projeto.ClienteProjeto.domain.request.ClienteRequest;
import Projeto.ClienteProjeto.domain.response.ClienteResponse;
import Projeto.ClienteProjeto.exception.ErrorBadRequest;
import Projeto.ClienteProjeto.exception.ErrorNotFound;
import Projeto.ClienteProjeto.openFeingConsuming.Consuming;
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
class CadastroPratoServiceTest {

    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private CadastroPratoService cadastrarPratosService;

    @Mock
    private Consuming consuming;

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

        Mockito.when(consuming.cadastrarPratos(clienteRequest)).thenReturn(clienteResponse);
        ClienteResponse result = cadastrarPratosService.cadastrarPratos(clienteRequest);
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

        Mockito.when(consuming.atualizarPrato(1L, clienteRequest)).thenReturn(clienteResponse);
        ClienteResponse result = cadastrarPratosService.atualizarPrato(1L, clienteRequest);
        assertEquals(clienteResponse, result);
        assertEquals(clienteResponse.getPrato(), result.getPrato());
        assertEquals(clienteResponse.getPais(), result.getPais());
        assertEquals(clienteResponse, result);
    }

    @Test
    void atualizarPratoErrorNotFoundId() {
        clienteRequest = new ClienteRequest("Feijoada", "Brasil");

        Mockito.when(consuming.atualizarPrato(Mockito.anyLong(), Mockito.any(ClienteRequest.class)))
                .thenThrow(new ErrorNotFound("Id não encontrado"));

        Exception exception = assertThrows(ErrorNotFound.class, () -> {
            cadastrarPratosService.atualizarPrato(2L, clienteRequest);
        });

        assertEquals("Id não encontrado", exception.getMessage());
    }

    @Test
    void cadastrarPratoErrorBadRequest() {
        clienteRequest = new ClienteRequest("", "Brasil"); // Prato vazio deve causar ErrorBadRequest

        Exception exception = assertThrows(ErrorBadRequest.class, () -> {
            cadastrarPratosService.cadastrarPratos(clienteRequest);
        });

        assertEquals("Nome e preço são obrigatórios", exception.getMessage());
    }

    @Test
    void atualizarPratoErrorBadRequest() {
        clienteRequest = new ClienteRequest("Feijoada", ""); // País vazio deve causar ErrorBadRequest

        Exception exception = assertThrows(ErrorBadRequest.class, () -> {
            cadastrarPratosService.atualizarPrato(1L, clienteRequest);
        });

        assertEquals("Prato e país são obrigatórios", exception.getMessage());
    }

}