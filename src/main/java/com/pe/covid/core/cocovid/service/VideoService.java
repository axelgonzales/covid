package com.pe.covid.core.cocovid.service;

import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.model.VideoRequest;

import java.util.List;
import java.util.Optional;

public interface VideoService {

    public List<VideoEntity> findAllvideos();

    public Optional<VideoEntity> findvideoById(Long id);

    public VideoEntity savevideo(VideoRequest VideoRequest);

    public VideoEntity updatevideo(VideoRequest VideoRequest, Long id);

    public void deletevideoById(Long id);
}
