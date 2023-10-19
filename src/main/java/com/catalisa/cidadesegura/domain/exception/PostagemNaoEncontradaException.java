package com.catalisa.cidadesegura.domain.exception;

public class PostagemNaoEncontradaException extends RuntimeException{
    private int status;
    public  PostagemNaoEncontradaException(String mensagem, int status){
        super(mensagem);
        this.status = status;
    }

    public PostagemNaoEncontradaException(){

    }

    public int getStatus() {
        return status;
    }
}
