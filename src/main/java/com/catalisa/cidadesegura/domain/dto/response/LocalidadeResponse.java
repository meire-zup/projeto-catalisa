package com.catalisa.cidadesegura.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadeResponse {

    private Long idLocalidade;
    private String ruaLocalidade;
    private Integer numeroLocalidade;
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;
    private CidadeResponse cidadeResponse;


}
