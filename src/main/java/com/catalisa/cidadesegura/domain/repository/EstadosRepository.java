package com.catalisa.cidadesegura.domain.repository;

import com.catalisa.cidadesegura.domain.model.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosRepository extends JpaRepository<EstadosModel,Long> {
}
