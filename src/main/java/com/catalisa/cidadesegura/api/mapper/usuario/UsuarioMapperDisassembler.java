package com.catalisa.cidadesegura.api.mapper.usuario;

import com.catalisa.cidadesegura.domain.dto.request.UsuarioRequest;
import com.catalisa.cidadesegura.domain.model.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel usuarioRequestParaUsuarioModel(UsuarioRequest usuarioRequest){

        return modelMapper.map(usuarioRequest, UsuarioModel.class);

    }

}
