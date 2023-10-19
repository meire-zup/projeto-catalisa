package com.catalisa.cidadesegura.domain.service;


import com.catalisa.cidadesegura.domain.model.PostagemModel;
import com.catalisa.cidadesegura.domain.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public List<PostagemModel> listar() {
        return postagemRepository.findAll();
    }

    public Optional<PostagemModel> listarPorId(Long idPostagem){return  postagemRepository.findById(idPostagem);}

    public Optional<PostagemModel> buscarPostagemPorIdEUsername(Long id,String username) {
        return postagemRepository.findByIdAndUsername(id,username);
    }

    public void deletarPostagem(Long id){
        postagemRepository.deleteById(id);
    }

    @Transactional
    public PostagemModel cadastrar(PostagemModel postagemModel) {
        return postagemRepository.save(postagemModel);
    }

    public PostagemModel salvar(PostagemModel postagemModel){return postagemRepository.save(postagemModel);}

    public List<PostagemModel> buscarPorCidade(String cidade) {
        return postagemRepository.findByCidade(cidade);
    }
    public List<PostagemModel> buscarPorBairro(String bairro) {
        return postagemRepository.findByBairro(bairro);
    }
}
