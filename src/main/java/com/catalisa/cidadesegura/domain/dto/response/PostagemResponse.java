package com.catalisa.cidadesegura.domain.dto.response;

import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.model.enums.TipoDePerigo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostagemResponse {
    private Long idPostagem;
    private UsuarioModel usuario;

    private LocalidadeModel localidade;

    private LocalDate dataPostagem;

    private TipoDePerigo tipo;

    private String descricao;

    private String dica;
}
