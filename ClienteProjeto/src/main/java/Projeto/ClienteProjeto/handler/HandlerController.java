package Projeto.ClienteProjeto.handler;

import Projeto.ClienteProjeto.domain.response.ResponseExceptions;
import Projeto.ClienteProjeto.exception.ErrorBadRequest;
import Projeto.ClienteProjeto.exception.ErrorNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(ErrorBadRequest.class)
    public ResponseEntity<ResponseExceptions> handleErrorBadRequest(ErrorBadRequest ex) {
        return new ResponseEntity<>(
                ResponseExceptions.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad Request Exception")
                        .detalhes(ex.getMessage())
                        .msgdev(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorNotFound.class)
    public ResponseEntity<ResponseExceptions> handleErroNotFound(ErrorNotFound ex) {
        return new ResponseEntity<>(
                ResponseExceptions.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Not Found Exception")
                        .detalhes(ex.getMessage())
                        .msgdev(ex.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
}