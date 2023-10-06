package com.catalisa.cidadesegura.api.mapper.localidade;

import com.catalisa.cidadesegura.domain.dto.response.CidadeResponse;
import com.catalisa.cidadesegura.domain.dto.response.LocalidadeResponse;
import com.catalisa.cidadesegura.domain.model.LocalidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocalidadeMapperAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public LocalidadeResponse localidadeModelParaLocalidadeResponse(LocalidadeModel localidadeModel) {
        LocalidadeResponse localidadeResponse = modelMapper.map(localidadeModel, LocalidadeResponse.class);
        localidadeResponse.setCidadeResponse(modelMapper.map(localidadeModel.getCidadesModel(), CidadeResponse.class));
        return localidadeResponse;
    }
    public LocalidadeResponse localidadeModelParaLocalidadeResponse2(LocalidadeModel localidadeModel) {
        LocalidadeResponse localidadeResponse = modelMapper.map(localidadeModel, LocalidadeResponse.class);
        return localidadeResponse;
    }

    public List<LocalidadeResponse> toCollectionLocalidadeResponse(List<LocalidadeModel> localidades) {

        List<LocalidadeResponse> localidadeResponses = new ArrayList<>();

        for (LocalidadeModel localidadeModel : localidades) {
            localidadeResponses.add(localidadeModelParaLocalidadeResponse2(localidadeModel));
        }

        return localidadeResponses;

    }

}

