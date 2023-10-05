package com.catalisa.cidadesegura.domain.model;

import com.catalisa.cidadesegura.domain.model.enums.TipoDePerigo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Postagens")
@AllArgsConstructor
@NoArgsConstructor
public class PostagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private LocalidadeModel localidade;

    private LocalDate dataPostagem;

    private TipoDePerigo tipo;

    private String descricao;

    private String dica;
}
