package com.catalisa.cidadesegura.api.mapper.postagem;

import com.catalisa.cidadesegura.domain.dto.response.PostagemResponse;

import com.catalisa.cidadesegura.domain.model.PostagemModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostagemMapperAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public PostagemResponse postagemModelParaPostagemResponse(PostagemModel postagemModel) {
        return modelMapper.map(postagemModel, PostagemResponse.class);
    }

    public List<PostagemResponse> toCollectionPostagemResponse(List<PostagemModel> postagens) {

        List<PostagemResponse> postagemResponses = new ArrayList<>();

        for (PostagemModel postagemModel : postagens) {
            postagemResponses.add(postagemModelParaPostagemResponse(postagemModel));
        }
        return postagemResponses;
    }
}
