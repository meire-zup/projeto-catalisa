package com.catalisa.cidadesegura.domain.repository;

import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadeRepository extends JpaRepository<LocalidadeModel, Long> {

}
