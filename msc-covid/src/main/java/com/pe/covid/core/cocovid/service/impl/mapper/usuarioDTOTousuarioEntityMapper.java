package com.pe.covid.core.cocovid.service.impl.mapper;

import org.modelmapper.ModelMapper;
import com.pe.covid.core.cocovid.domain.usuarioEntity;
import com.pe.covid.core.cocovid.model.usuarioRequest;

public class usuarioDTOTousuarioEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public usuarioEntity usuarioDTOTousuarioEntityMapper(usuarioRequest usuarioRequest) {
        return modelMapper.map(usuarioRequest, usuarioEntity.class);
    }
}