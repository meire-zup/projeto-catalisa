package com.catalisa.cidadesegura.domain.dto.request;

import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.model.enums.TipoDePerigo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostagemRequest {
    private UsuarioRequest usuario;
    private LocalidadeRequest localidade;
    private TipoDePerigo tipo;
    private String descricao;
    private String dica;
}
