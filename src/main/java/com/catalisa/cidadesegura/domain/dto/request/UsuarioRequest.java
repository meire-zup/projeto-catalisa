package com.catalisa.cidadesegura.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank
    private String nomeUsuario;

    @NotBlank
    @Email
    private String emailUsuario;


}
