package com.catalisa.cidadesegura.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Cidades")
@AllArgsConstructor
@NoArgsConstructor
public class CidadesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;

    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadosModel estado;
    @OneToMany(mappedBy = "cidadesModel")
    @JsonIgnore
    private List<LocalidadeModel> localidades = new ArrayList<>();
}
