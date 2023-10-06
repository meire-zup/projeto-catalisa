package com.catalisa.cidadesegura.domain.repository;

import com.catalisa.cidadesegura.domain.model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {
}
