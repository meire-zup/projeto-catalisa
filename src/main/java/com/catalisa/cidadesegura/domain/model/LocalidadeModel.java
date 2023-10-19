package com.catalisa.cidadesegura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "localidade")
@Data
public class LocalidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalidade;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'rua' não pode ser vazio.")
    private String ruaLocalidade;

    private Integer numeroLocalidade;

    @Column(nullable = false)
    @NotEmpty(message = "Campo 'bairroLocalidade' não pode ser vazio.")
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadesModel cidadesModel;
}
