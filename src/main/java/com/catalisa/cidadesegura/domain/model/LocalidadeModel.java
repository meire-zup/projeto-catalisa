package com.catalisa.cidadesegura.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Localidade")
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class LocalidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalidade;

    private String ruaLocalidade;
    private Integer numeroLocalidade;
    private String bairroLocalidade;
    private String pontoReferenciaLocalidade;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadesModel cidadesModel;

}
