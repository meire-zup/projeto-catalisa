package com.catalisa.cidadesegura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "Usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    @NotBlank
    private String nomeUsuario;

    @Column(nullable = false)
    @NotBlank
    @Email
    private String emailUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PostagemModel> postagens;

}
