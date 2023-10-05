package com.catalisa.cidadesegura.domain.service;

import com.catalisa.cidadesegura.domain.exception.CidadeNaoEncontradaException;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import com.catalisa.cidadesegura.domain.repository.CidadesRepository;
import com.catalisa.cidadesegura.domain.repository.EstadosRepository;
import com.catalisa.cidadesegura.domain.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository localidadeRepository;

    @Autowired
    private CidadesRepository cidadesRepository;

    @Autowired
    private EstadosRepository estadosRepository;
    public CidadesModel buscarPorId(Long idCidade) {

        return cidadesRepository.findById(idCidade)
                .orElseThrow(() -> new CidadeNaoEncontradaException(idCidade));
    }

    @Transactional
    public LocalidadeModel salvar(LocalidadeModel localidadeModel) {

        return localidadeRepository.save(localidadeModel);

    }
}
