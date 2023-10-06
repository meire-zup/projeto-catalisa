package com.catalisa.cidadesegura.domain.model;

import com.catalisa.cidadesegura.domain.model.enums.TipoDePerigo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    private LocalDate dataPostagem = LocalDate.now();

    @Column(nullable = false)
    @NotNull(message = "Campo 'tipo' não pode ser vazio.")
    private TipoDePerigo tipo;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'descricao' não pode ser vazio.")
    private String descricao;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'dica' não pode ser vazio.")
    private String dica;
}
