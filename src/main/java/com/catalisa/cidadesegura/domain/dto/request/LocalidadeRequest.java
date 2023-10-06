package com.catalisa.cidadesegura.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LocalidadeRequest {

    private String ruaLocalidade;
    private Integer numeroLocalidade;
    @NotBlank
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;

    private CidadeIdRequest cidadesModel;

}
