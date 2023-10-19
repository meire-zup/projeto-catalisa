package com.catalisa.cidadesegura.domain.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);

    }

    public UsuarioNaoEncontradoException(Long idUsusario){

        this(String.format("Não existe um cadastro de usuário com id %d", idUsusario));

    }

}