package com.catalisa.cidadesegura.api.mapper.cidade;

import com.catalisa.cidadesegura.domain.dto.response.CidadeResponse;
import com.catalisa.cidadesegura.domain.dto.response.EstadoResponse;
import com.catalisa.cidadesegura.domain.model.CidadesModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CidadeMapperAssembler {
    @Autowired
    private ModelMapper modelMapper;
   public CidadeResponse cidadeModelParaCidadeResponse(CidadesModel cidadesModel) {
        CidadeResponse cidadeResponse = modelMapper.map(cidadesModel, CidadeResponse.class);
        cidadeResponse.setEstadoResponse(modelMapper.map(cidadesModel.getEstado(), EstadoResponse.class));
        return cidadeResponse;
    }
    public List<CidadeResponse> toCollectionCidadeResponse(List<CidadesModel> cidadesModels) {

        List<CidadeResponse> cidadeResponses = new ArrayList<>();

        for (CidadesModel cidadeModel : cidadesModels) {
            cidadeResponses.add(cidadeModelParaCidadeResponse(cidadeModel));
        }

        return cidadeResponses;

    }
}
