package com.catalisa.cidadesegura.api.mapper.usuario;

import com.catalisa.cidadesegura.domain.dto.response.UsuarioResponse;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UsuarioMapperAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioResponse usuarioModelParaUsuarioResponse(UsuarioModel usuarioModel) {

        return modelMapper.map(usuarioModel, UsuarioResponse.class);

    }

    public List<UsuarioResponse> toCollectionUsuarioResponse(List<UsuarioModel> usuarios) {

        List<UsuarioResponse> usuarioResponses = new ArrayList<>();

        for (UsuarioModel usuarioModel : usuarios) {
            usuarioResponses.add(usuarioModelParaUsuarioResponse(usuarioModel));
        }

        return usuarioResponses;

    }

}
