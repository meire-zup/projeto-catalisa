package com.catalisa.cidadesegura.domain.service;

import com.catalisa.cidadesegura.domain.exception.LocalidadeNaoEncontradaException;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocalidadeService {
    @Autowired
    private LocalidadeRepository localidadeRepository;

    public List<LocalidadeModel> listar() {

        return localidadeRepository.findAll();

    }

    public LocalidadeModel buscarPorId(Long idLocalidae) {

        return localidadeRepository.findById(idLocalidae)
                .orElseThrow(() -> new LocalidadeNaoEncontradaException(idLocalidae));
    }

    @Transactional
    public LocalidadeModel salvar(LocalidadeModel localidadeModel) {

        return localidadeRepository.save(localidadeModel);

    }

    public void excluir(Long idLocalidade) {

        try {
            localidadeRepository.deleteById(idLocalidade);
        } catch (EmptyResultDataAccessException ex) {

            throw new LocalidadeNaoEncontradaException(idLocalidade);

        }
    }

}
