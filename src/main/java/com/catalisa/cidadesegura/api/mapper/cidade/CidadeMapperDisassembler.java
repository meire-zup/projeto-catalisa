package com.catalisa.cidadesegura.api.mapper.cidade;

import com.catalisa.cidadesegura.domain.dto.request.CidadeIdRequest;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import com.catalisa.cidadesegura.domain.model.EstadosModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapperDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public CidadesModel cidadeRequestParaCidadeModel(CidadeIdRequest cidadeRequest) {

        return modelMapper.map(cidadeRequest, CidadesModel.class);

    }

    public void copyToDomainObject(CidadeIdRequest cidadeRequest, CidadesModel cidadesModel){

        cidadesModel.setEstado(new EstadosModel());

        modelMapper.map(cidadeRequest, cidadesModel);

    }
}
