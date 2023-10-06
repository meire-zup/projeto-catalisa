package com.catalisa.cidadesegura.domain.service;

import com.catalisa.cidadesegura.domain.dto.response.PostagemResponse;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
import com.catalisa.cidadesegura.domain.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public List<PostagemModel> listar() {
        return postagemRepository.findAll();
    }

    @Transactional
    public PostagemModel cadastrar(PostagemModel postagemModel) {
        return postagemRepository.save(postagemModel);
    }


}
