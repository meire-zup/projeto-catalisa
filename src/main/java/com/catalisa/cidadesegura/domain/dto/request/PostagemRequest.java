package com.catalisa.cidadesegura.domain.dto.request;

import com.catalisa.cidadesegura.domain.model.enums.TipoDePerigo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostagemRequest {

    private LocalidadePostagemRequest localidade;

    @Column(nullable = false)
    @NotNull(message = "Campo 'tipo' não pode ser vazio.")
    private TipoDePerigo tipo;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'descricao' não pode ser vazio.")
    private String descricao;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'dica' não pode ser vazio.")
    private String dica;
}
