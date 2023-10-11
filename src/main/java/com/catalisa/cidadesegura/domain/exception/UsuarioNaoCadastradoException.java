package com.catalisa.cidadesegura.domain.exception;

public class UsuarioNaoCadastradoException extends RuntimeException{
    public  UsuarioNaoCadastradoException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoCadastradoException(){

    }
}
