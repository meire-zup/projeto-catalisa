package com.catalisa.cidadesegura.domain.repository;

import com.catalisa.cidadesegura.domain.model.CidadesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CidadesRepository extends JpaRepository<CidadesModel,Long> {


}
