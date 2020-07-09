package com.pe.covid.core.cocovid.service.impl.mapper;

import org.modelmapper.ModelMapper;
import com.pe.covid.core.cocovid.domain.videoEntity;
import com.pe.covid.core.cocovid.model.videoRequest;

public class videoDTOTovideoEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public videoEntity videoDTOTovideoEntityMapper(videoRequest videoRequest) {
        return modelMapper.map(videoRequest, videoEntity.class);
    }
}