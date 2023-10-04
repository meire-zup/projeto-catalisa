package com.catalisa.cidadesegura.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    private String nomeEstado;
    private String uf;
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<CidadesModel> cidades;
}
