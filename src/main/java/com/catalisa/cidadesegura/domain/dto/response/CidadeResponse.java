package com.catalisa.cidadesegura.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResponse {

    private Long idCidade;
    private String nomeCidade;
    private EstadoResponse estadoResponse;
}
