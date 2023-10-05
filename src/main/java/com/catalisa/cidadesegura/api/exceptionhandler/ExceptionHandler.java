package com.catalisa.cidadesegura.api.exceptionhandler;

import com.catalisa.cidadesegura.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
            UsuarioNaoEncontradoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getLocalizedMessage();
        Problema problema = Problema.builder()
                .status(status.value())
                .title("Usuário não encontrado")
                .detail(detail)
                .build();

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
