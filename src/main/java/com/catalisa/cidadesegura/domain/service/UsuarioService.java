package com.catalisa.cidadesegura.domain.service;

import com.catalisa.cidadesegura.domain.dto.response.UsuarioResponse;
import com.catalisa.cidadesegura.domain.exception.UsuarioNaoEncontradoException;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import com.catalisa.cidadesegura.security.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> listar() {

        return usuarioRepository.findAll();

    }

    public UsuarioModel buscarPorId(Long idUsuario) {

        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));
    }

    @Transactional
    public UsuarioModel salvar(UsuarioModel usuarioModel) {

        return usuarioRepository.save(usuarioModel);

    }

    public void excluir(Long idUsuario) {

        try {
            usuarioRepository.deleteById(idUsuario);
        } catch (EmptyResultDataAccessException ex) {

            throw new UsuarioNaoEncontradoException(idUsuario);

        }
    }

    @Transactional
    public ResponseEntity<?> salvar(UsuarioModel usuario, HttpStatus status) {
        if (usuario.existeOutroUsuarioComMesmoEmail(usuarioRepository)) {
            return ResponseEntity.badRequest().body("Já existe usuário cadastrado com mesmo e-mail..");
        } else if (usuario.existeOutroUsuarioComMesmoUsername(usuarioRepository)) {
            return ResponseEntity.badRequest().body("Já existe usuário cadastrado com mesmo username.");
        } else if (usuario.existeOutroUsuarioComMesmoPassword(usuarioRepository)) {
            return ResponseEntity.badRequest().body("Já existe usuário cadastrado com mesma senha.");
        } else {

            usuario = usuarioRepository.save(usuario);
            usuario.setRole(RoleEnum.USER);
            //usuarioRepository.adicionarUsuarioRole(usuario.getIdUsuario(), 1L);
            return ResponseEntity
                    .status(status)
                    .body(UsuarioResponse.toResponse(usuario));
        }
    }
}
