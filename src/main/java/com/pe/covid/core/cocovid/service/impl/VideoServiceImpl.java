package com.pe.covid.core.cocovid.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pe.covid.core.cocovid.constant.Constant;
import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.exception.ModelNotFoundException;
import com.pe.covid.core.cocovid.model.VideoRequest;
import com.pe.covid.core.cocovid.repository.VideoRepository;
import com.pe.covid.core.cocovid.service.VideoService;
import com.pe.covid.core.cocovid.service.impl.mapper.VideoDTOToVideoEntityMapper;


@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final VideoRepository VideoRepository;
    private VideoDTOToVideoEntityMapper VideoDTOToVideoEntityMapper = new VideoDTOToVideoEntityMapper();
   
    private AmazonClientService amazonClientService;

    
    
    @Autowired
    public VideoServiceImpl(VideoRepository VideoRepository , AmazonClientService amazonClientService  ) {
        this.VideoRepository = VideoRepository;
        this.amazonClientService = amazonClientService;
    }

    public List<VideoEntity> findAllvideos() {

        List<VideoEntity> listvideo = VideoRepository.findAll();

        return listvideo;
    }

    public Optional<VideoEntity> findvideoById(Long id) {

        Optional<VideoEntity> optionalvideo = VideoRepository.findById(id);

        return optionalvideo;
    }

    public VideoEntity savevideo(VideoRequest VideoRequest, MultipartFile video, MultipartFile imagen) {
    	VideoRequest.setPath(amazonClientService.uploadFile("covid", "video", video));
    	VideoRequest.setRuta(amazonClientService.uploadFile("covid", "imagen", imagen));
        VideoEntity VideoEntity = VideoRepository.save(VideoDTOToVideoEntityMapper.videoDTOTovideoEntityMapper(VideoRequest));
        
        return VideoEntity;
    }

    public VideoEntity updatevideo(VideoRequest VideoRequest, Long id) {

        return VideoRepository.findById(id).map(videoRequestObje -> {
            VideoRequest.setId(id);
            VideoEntity video = VideoRepository.save(VideoDTOToVideoEntityMapper.videoDTOTovideoEntityMapper(VideoRequest));

        return video;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deletevideoById(Long id) {

        VideoRepository.findById(id).ifPresent(delete -> VideoRepository.deleteById(id));

    }
}