package com.catalisa.cidadesegura.api.mapper.postagem;

import com.catalisa.cidadesegura.domain.dto.request.LocalidadeRequest;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalidadeMapperDisassembler {
    @Autowired
    private ModelMapper modelMapper;
    public LocalidadeModel localidadeRequestParaLocalidadeModel(LocalidadeRequest localidadeRequest){

        return modelMapper.map(localidadeRequest, LocalidadeModel.class);
    }
}
