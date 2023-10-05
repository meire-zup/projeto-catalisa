package com.catalisa.cidadesegura.domain.model;

import javax.persistence.*;

@Entity
public class PostagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostagem;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;
}
