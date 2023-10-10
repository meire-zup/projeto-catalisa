package com.catalisa.cidadesegura.api.apiexceptionhandler;

import com.catalisa.cidadesegura.domain.exception.CidadeNaoEncontradaException;
import com.catalisa.cidadesegura.domain.exception.PostagemNaoEncontradaException;
import com.catalisa.cidadesegura.domain.exception.UsuarioNaoCadastradoException;
import com.catalisa.cidadesegura.domain.exception.UsuarioNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
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

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
        StringBuilder messages = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            messages.append(propertyPath).append(": ").append(errorMessage).append("\n");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages.toString());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CidadeNaoEncontradaException.class)
    public ResponseEntity<String> handleCidadeNaoEncontradaException(CidadeNaoEncontradaException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UsuarioNaoCadastradoException.class)
    public ResponseEntity<String> handleUsuarioNaoCadastradoException(UsuarioNaoCadastradoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PostagemNaoEncontradaException.class)
    public ResponseEntity<String> handlePostagemNaoEncontradaException(PostagemNaoEncontradaException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

