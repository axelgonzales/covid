package com.pe.covid.core.cocovid.service;

import com.pe.covid.core.cocovid.controller.request.VideoFilterRequest;
import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.model.VideoRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    public List<VideoEntity> findAllvideos();

    public Optional<VideoEntity> findvideoById(Long id);
   
    public VideoEntity savevideo(VideoRequest VideoRequest, MultipartFile video, MultipartFile imagen);

    public VideoEntity updatevideo(VideoRequest VideoRequest, Long id);

    public void deletevideoById(Long id);

	public List<VideoEntity> searchVideo(VideoFilterRequest request);
}
