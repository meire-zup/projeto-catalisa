package com.catalisa.cidadesegura.domain.service;

import com.catalisa.cidadesegura.domain.exception.UsuarioNaoEncontradoException;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import com.catalisa.cidadesegura.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

}
