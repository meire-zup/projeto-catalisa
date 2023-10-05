package com.catalisa.cidadesegura.domain.dto.request;

import com.catalisa.cidadesegura.domain.model.CidadesModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class LocalidadeRequest {

    private Long idEstado;
    private String ruaLocalidade;
    private Integer numeroLocalidade;
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;

    private Long IdCidade;
}
