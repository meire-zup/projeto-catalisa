package com.catalisa.cidadesegura.domain.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeIdRequest {

    @NotNull
    private Long idCidade;

}
