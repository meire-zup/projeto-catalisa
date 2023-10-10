package com.catalisa.cidadesegura.domain.dto.request;

import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UsuarioModel toModel() {

        String password = new BCryptPasswordEncoder().encode(this.password);

        return new UsuarioModel(this.nomeUsuario, this.emailUsuario, this.username, password);
    }
}


