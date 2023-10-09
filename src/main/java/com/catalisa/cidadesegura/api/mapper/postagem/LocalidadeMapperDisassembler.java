package com.catalisa.cidadesegura.api.mapper.postagem;

import com.catalisa.cidadesegura.domain.dto.request.LocalidadePostagemRequest;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalidadeMapperDisassembler {
    @Autowired
    private ModelMapper modelMapper;
    public LocalidadeModel localidadeRequestParaLocalidadeModel(LocalidadePostagemRequest localidadePostagemRequest){

        return modelMapper.map(localidadePostagemRequest, LocalidadeModel.class);
    }
}
