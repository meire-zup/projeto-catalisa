package com.catalisa.cidadesegura.api.mapper.estado;

import com.catalisa.cidadesegura.domain.dto.response.EstadoResponse;
import com.catalisa.cidadesegura.domain.model.EstadosModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstadoMapperAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public EstadoResponse estadoModelParaEstadoResponse(EstadosModel estadosModel) {

        return modelMapper.map(estadosModel, EstadoResponse.class);

    }

    public List<EstadoResponse> toCollectionEstadoResponse(List<EstadosModel> estadosModels) {

        List<EstadoResponse> estadoResponses = new ArrayList<>();

        for (EstadosModel estadoModel : estadosModels) {
            estadoResponses.add(estadoModelParaEstadoResponse(estadoModel));
        }

        return estadoResponses;

    }
}
