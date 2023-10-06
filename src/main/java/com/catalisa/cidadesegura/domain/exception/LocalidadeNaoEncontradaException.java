package com.catalisa.cidadesegura.domain.exception;

public class LocalidadeNaoEncontradaException extends RuntimeException {

    public LocalidadeNaoEncontradaException(String mensagem){
        super(mensagem);

    }

    public LocalidadeNaoEncontradaException(Long idLocalidade){

        this(String.format("Não existe um cadastro de localidade com id %d", idLocalidade));

    }
}
