package com.catalisa.cidadesegura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String nomeUsuario;

    private String emailUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PostagemModel> postagens;

}
