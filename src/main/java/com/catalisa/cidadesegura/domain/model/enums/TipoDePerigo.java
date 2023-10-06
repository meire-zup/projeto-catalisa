package com.catalisa.cidadesegura.domain.model.enums;

public enum TipoDePerigo {
    ASSEDIO("Assédio"),
    ROUBO("Roubo"),
    OUTROS("Outro");

    private final String descricao;

    TipoDePerigo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
