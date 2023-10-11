package com.catalisa.cidadesegura.domain.repository;

import com.catalisa.cidadesegura.domain.model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

    @Query("SELECT p FROM PostagemModel p WHERE p.localidade.cidadesModel.nomeCidade LIKE %:cidade%")
    List<PostagemModel> findByCidade(@Param("cidade") String cidade);

    @Query("SELECT p FROM PostagemModel p WHERE p.localidade.bairroLocalidade LIKE %:bairro%")
    List<PostagemModel> findByBairro(@Param("bairro") String bairro);

    @Query("SELECT p FROM PostagemModel p WHERE p.id = :postId AND p.usuario.username = :username")
    Optional<PostagemModel> findByIdAndUsername(@Param("postId") Long postId, @Param("username") String username);
}
