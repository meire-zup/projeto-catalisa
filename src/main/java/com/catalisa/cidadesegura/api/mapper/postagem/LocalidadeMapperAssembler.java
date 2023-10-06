package com.catalisa.cidadesegura.api.mapper.postagem;

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
        return modelMapper.map(localidadeModel, LocalidadeResponse.class);
    }

    public List<LocalidadeResponse> toCollectionLocalidadeResponse(List<LocalidadeModel> localidades) {

        List<LocalidadeResponse> localidadeResponses = new ArrayList<>();

        for (LocalidadeModel localidadeModel : localidades) {
            localidadeResponses.add(localidadeModelParaLocalidadeResponse(localidadeModel));
        }
        return localidadeResponses;
    }
}
