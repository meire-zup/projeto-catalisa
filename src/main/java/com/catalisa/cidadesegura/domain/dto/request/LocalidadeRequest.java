package com.catalisa.cidadesegura.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LocalidadeRequest {

    private String ruaLocalidade;
    private Integer numeroLocalidade;
    @NotBlank
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;

    @Valid
    @NotNull
    private CidadeRequest idCidade;

}
