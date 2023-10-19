package com.catalisa.cidadesegura.domain.dto.response;

import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private Long idUsuario;

    private String nomeUsuario;

    private String emailUsuario;


    public static UsuarioResponse toResponse(UsuarioModel usuario) {

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setIdUsuario(usuario.getIdUsuario());
        usuarioResponse.setNomeUsuario(usuario.getNomeUsuario());
        usuarioResponse.setEmailUsuario(usuario.getEmailUsuario());
        return usuarioResponse;
    }
}
