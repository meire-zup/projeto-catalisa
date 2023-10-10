package com.catalisa.cidadesegura.domain.repository;

import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByUsername(String username);
    Optional<UsuarioModel> findByEmailUsuario(String email);
    Optional<UsuarioModel> findByPassword(String password);
    @Modifying
    @Query(value = "INSERT INTO usuarios_roles (id_usuario, id_role) VALUES (:usuarioId, :roleId)", nativeQuery = true)
    void adicionarUsuarioRole(Long usuarioId, Long roleId);
}
