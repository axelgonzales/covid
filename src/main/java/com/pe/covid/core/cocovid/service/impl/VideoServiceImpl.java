package com.pe.covid.core.cocovid.service.impl;

import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.repository.VideoRepository;
import com.pe.covid.core.cocovid.model.VideoRequest;
import com.pe.covid.core.cocovid.service.impl.mapper.VideoDTOToVideoEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.covid.core.cocovid.service.VideoService;
import com.pe.covid.core.cocovid.exception.ModelNotFoundException;
import com.pe.covid.core.cocovid.constant.Constant;

import io.opentracing.Span;
import io.opentracing.Tracer;

import pe.financieraoh.framework.message.MessageProducer;
import pe.financieraoh.framework.model.MessageModel;
import pe.financieraoh.framework.config.TraceAndSpan;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final VideoRepository VideoRepository;
    private VideoDTOToVideoEntityMapper VideoDTOToVideoEntityMapper = new VideoDTOToVideoEntityMapper();
    private Tracer tracer;
    private MessageProducer messageProducer;
    private TraceAndSpan traceAndSpan = new TraceAndSpan();

    @Autowired
    public VideoServiceImpl(VideoRepository VideoRepository,  Tracer tracer, MessageProducer messageProducer) {
        this.VideoRepository = VideoRepository;
        this.tracer = tracer;
        this.messageProducer = messageProducer;
    }

    public List<VideoEntity> findAllvideos() {
        Span span = tracer.buildSpan("GET ALL video").start();

        List<VideoEntity> listvideo = VideoRepository.findAll();

        messageProducer.sendMessage("GET ALL video MESSAGE TEST" + "-" +  traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return listvideo;
    }

    public Optional<VideoEntity> findvideoById(Long id) {
        Span span = tracer.buildSpan("GET video/" + id).start();

        Optional<VideoEntity> optionalvideo = VideoRepository.findById(id);

        messageProducer.sendMessage("GET video/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return optionalvideo;
    }

    public VideoEntity savevideo(VideoRequest VideoRequest) {
        Span span = tracer.buildSpan("POST video").start();

        VideoEntity VideoEntity = VideoRepository.save(VideoDTOToVideoEntityMapper.videoDTOTovideoEntityMapper(VideoRequest));

        messageProducer.sendMessage("POST video MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return VideoEntity;
    }

    public VideoEntity updatevideo(VideoRequest VideoRequest, Long id) {

        Span span = tracer.buildSpan("UPDATE video/" + id).start();


        return VideoRepository.findById(id).map(videoRequestObje -> {
            VideoRequest.setId(id);
            VideoEntity video = VideoRepository.save(VideoDTOToVideoEntityMapper.videoDTOTovideoEntityMapper(VideoRequest));
            messageProducer.sendMessage("UPDATE video MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
            span.finish();
        return video;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deletevideoById(Long id) {
        Span span = tracer.buildSpan("DELETE video/" + id).start();

        VideoRepository.findById(id).ifPresent(delete -> VideoRepository.deleteById(id));

        messageProducer.sendMessage("DELETE video/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
    }
}