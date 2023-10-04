package com.catalisa.cidadesegura.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private Long idUsuario;

    private String nomeUsuario;

    private String emailUsuario;
}
