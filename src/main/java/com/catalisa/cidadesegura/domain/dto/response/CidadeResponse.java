package com.catalisa.cidadesegura.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CidadeResponse {

    @Column(name = "id_cidade")
    private Long idCidade;
    private String nomeCidade;
    private EstadoResponse estadoResponse;


}
