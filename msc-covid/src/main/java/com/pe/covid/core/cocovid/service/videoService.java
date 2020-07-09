package com.pe.covid.core.cocovid.service;

import com.pe.covid.core.cocovid.domain.videoEntity;
import com.pe.covid.core.cocovid.model.videoRequest;

import java.util.List;
import java.util.Optional;

public interface videoService {

    public List<videoEntity> findAllvideos();

    public Optional<videoEntity> findvideoById(Long id);

    public videoEntity savevideo(videoRequest videoRequest);

    public videoEntity updatevideo(videoRequest videoRequest, Long id);

    public void deletevideoById(Long id);
}
