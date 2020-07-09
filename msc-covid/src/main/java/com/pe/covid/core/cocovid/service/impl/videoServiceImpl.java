package com.pe.covid.core.cocovid.service.impl;

import com.pe.covid.core.cocovid.domain.videoEntity;
import com.pe.covid.core.cocovid.repository.videoRepository;
import com.pe.covid.core.cocovid.model.videoRequest;
import com.pe.covid.core.cocovid.service.impl.mapper.videoDTOTovideoEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.covid.core.cocovid.service.videoService;
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
public class videoServiceImpl implements videoService {

    private final videoRepository videoRepository;
    private videoDTOTovideoEntityMapper videoDTOTovideoEntityMapper = new videoDTOTovideoEntityMapper();
    private Tracer tracer;
    private MessageProducer messageProducer;
    private TraceAndSpan traceAndSpan = new TraceAndSpan();

    @Autowired
    public videoServiceImpl(videoRepository videoRepository,  Tracer tracer, MessageProducer messageProducer) {
        this.videoRepository = videoRepository;
        this.tracer = tracer;
        this.messageProducer = messageProducer;
    }

    public List<videoEntity> findAllvideos() {
        Span span = tracer.buildSpan("GET ALL video").start();

        List<videoEntity> listvideo = videoRepository.findAll();

        messageProducer.sendMessage("GET ALL video MESSAGE TEST" + "-" +  traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return listvideo;
    }

    public Optional<videoEntity> findvideoById(Long id) {
        Span span = tracer.buildSpan("GET video/" + id).start();

        Optional<videoEntity> optionalvideo = videoRepository.findById(id);

        messageProducer.sendMessage("GET video/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return optionalvideo;
    }

    public videoEntity savevideo(videoRequest videoRequest) {
        Span span = tracer.buildSpan("POST video").start();

        videoEntity videoEntity = videoRepository.save(videoDTOTovideoEntityMapper.videoDTOTovideoEntityMapper(videoRequest));

        messageProducer.sendMessage("POST video MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return videoEntity;
    }

    public videoEntity updatevideo(videoRequest videoRequest, Long id) {

        Span span = tracer.buildSpan("UPDATE video/" + id).start();


        return videoRepository.findById(id).map(videoRequestObje -> {
            videoRequest.setId(id);
            videoEntity video = videoRepository.save(videoDTOTovideoEntityMapper.videoDTOTovideoEntityMapper(videoRequest));
            messageProducer.sendMessage("UPDATE video MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
            span.finish();
        return video;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deletevideoById(Long id) {
        Span span = tracer.buildSpan("DELETE video/" + id).start();

        videoRepository.findById(id).ifPresent(delete -> videoRepository.deleteById(id));

        messageProducer.sendMessage("DELETE video/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
    }
}