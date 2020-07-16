package com.pe.covid.core.cocovid.service.impl.mapper;

import org.modelmapper.ModelMapper;
import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.model.VideoRequest;

public class VideoDTOToVideoEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public VideoEntity videoDTOTovideoEntityMapper(VideoRequest VideoRequest) {
        return modelMapper.map(VideoRequest, VideoEntity.class);
    }
}