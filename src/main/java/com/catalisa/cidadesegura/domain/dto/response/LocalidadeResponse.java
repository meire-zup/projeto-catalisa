package com.catalisa.cidadesegura.domain.dto.response;

import com.catalisa.cidadesegura.domain.model.CidadesModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadeResponse {
    private String ruaLocalidade;
    private Integer numeroLocalidade;
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;
    private CidadesModel cidadesModel;
}
