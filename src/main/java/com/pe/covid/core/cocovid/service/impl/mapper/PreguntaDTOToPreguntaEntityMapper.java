package com.pe.covid.core.cocovid.service.impl.mapper;

import org.modelmapper.ModelMapper;
import com.pe.covid.core.cocovid.domain.PreguntaEntity;
import com.pe.covid.core.cocovid.model.PreguntaRequest;

public class PreguntaDTOToPreguntaEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public PreguntaEntity preguntaDTOToPreguntaEntityMapper(PreguntaRequest preguntaRequest) {
        return modelMapper.map(preguntaRequest, PreguntaEntity.class);
    }
}