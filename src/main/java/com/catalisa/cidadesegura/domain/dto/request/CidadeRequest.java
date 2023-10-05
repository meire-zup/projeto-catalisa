package com.catalisa.cidadesegura.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeRequest {

    @NotNull
    private Long idCidade;

    @Valid
    @NotNull
    private EstadoRequest idEstado;

}
