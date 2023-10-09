package com.catalisa.cidadesegura.api.mapper.postagem;

import com.catalisa.cidadesegura.domain.dto.response.LocalidadePostagemResponse;
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

    public LocalidadePostagemResponse localidadeModelParaLocalidadeResponse(LocalidadeModel localidadeModel) {
        return modelMapper.map(localidadeModel, LocalidadePostagemResponse.class);
    }

    public List<LocalidadePostagemResponse> toCollectionLocalidadeResponse(List<LocalidadeModel> localidades) {

        List<LocalidadePostagemResponse> localidadePostagemRespons = new ArrayList<>();

        for (LocalidadeModel localidadeModel : localidades) {
            localidadePostagemRespons.add(localidadeModelParaLocalidadeResponse(localidadeModel));
        }
        return localidadePostagemRespons;
    }
}
