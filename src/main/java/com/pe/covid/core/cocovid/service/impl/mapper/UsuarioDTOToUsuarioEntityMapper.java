package com.pe.covid.core.cocovid.service.impl.mapper;

import org.modelmapper.ModelMapper;
import com.pe.covid.core.cocovid.domain.UsuarioEntity;
import com.pe.covid.core.cocovid.model.UsuarioRequest;

public class UsuarioDTOToUsuarioEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public UsuarioEntity usuarioDTOTousuarioEntityMapper(UsuarioRequest UsuarioRequest) {
        return modelMapper.map(UsuarioRequest, UsuarioEntity.class);
    }
}