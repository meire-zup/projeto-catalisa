package com.catalisa.cidadesegura.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //inclui nas propriedades somente os valores que não tiverem nulos
public class Problema {

    private Integer status;
    private String type;
    private String title;
    private String detail;

}
