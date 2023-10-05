package com.catalisa.cidadesegura.api.mapper.postagem;

import com.catalisa.cidadesegura.domain.dto.request.PostagemRequest;
import com.catalisa.cidadesegura.domain.model.PostagemModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostagemMapperDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public PostagemModel postagemRequestParaPostagemModel(PostagemRequest postagemRequest){

        return modelMapper.map(postagemRequest, PostagemModel.class);
    }
}
