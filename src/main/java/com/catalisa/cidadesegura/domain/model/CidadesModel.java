package com.catalisa.cidadesegura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cidade")
public class CidadesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;

    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadosModel estado;
}
