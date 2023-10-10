package com.catalisa.cidadesegura.domain.exception;

public class PostagemNaoEncontradaException extends RuntimeException{
    public  PostagemNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public PostagemNaoEncontradaException(){

    }
}
