package com.catalisa.cidadesegura.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LocalidadeRequest {
    @Column(nullable = false)
    @NotEmpty(message = "Campo 'ruaLocalidade' não pode ser vazio.")
    private String ruaLocalidade;

    private Integer numeroLocalidade;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'bairroLocalidade' não pode ser vazio.")
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'idCidade' não pode ser vazio.")
    private Long idCidade;
}
