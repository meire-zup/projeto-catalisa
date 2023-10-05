package com.catalisa.cidadesegura.domain.exception;

public class CidadeNaoEncontradaException extends RuntimeException{
    public  CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long idCidade){

        this(String.format("Não existe cidade com id %d", idCidade));

    }

}
