package com.pe.covid.core.cocovid.service.impl;

import com.pe.covid.core.cocovid.domain.usuarioEntity;
import com.pe.covid.core.cocovid.repository.usuarioRepository;
import com.pe.covid.core.cocovid.model.usuarioRequest;
import com.pe.covid.core.cocovid.service.impl.mapper.usuarioDTOTousuarioEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.covid.core.cocovid.service.usuarioService;
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
public class usuarioServiceImpl implements usuarioService {

    private final usuarioRepository usuarioRepository;
    private usuarioDTOTousuarioEntityMapper usuarioDTOTousuarioEntityMapper = new usuarioDTOTousuarioEntityMapper();
    private Tracer tracer;
    private MessageProducer messageProducer;
    private TraceAndSpan traceAndSpan = new TraceAndSpan();

    @Autowired
    public usuarioServiceImpl(usuarioRepository usuarioRepository,  Tracer tracer, MessageProducer messageProducer) {
        this.usuarioRepository = usuarioRepository;
        this.tracer = tracer;
        this.messageProducer = messageProducer;
    }

    public List<usuarioEntity> findAllusuarios() {
        Span span = tracer.buildSpan("GET ALL usuario").start();

        List<usuarioEntity> listusuario = usuarioRepository.findAll();

        messageProducer.sendMessage("GET ALL usuario MESSAGE TEST" + "-" +  traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return listusuario;
    }

    public Optional<usuarioEntity> findusuarioById(Long id) {
        Span span = tracer.buildSpan("GET usuario/" + id).start();

        Optional<usuarioEntity> optionalusuario = usuarioRepository.findById(id);

        messageProducer.sendMessage("GET usuario/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return optionalusuario;
    }

    public usuarioEntity saveusuario(usuarioRequest usuarioRequest) {
        Span span = tracer.buildSpan("POST usuario").start();

        usuarioEntity usuarioEntity = usuarioRepository.save(usuarioDTOTousuarioEntityMapper.usuarioDTOTousuarioEntityMapper(usuarioRequest));

        messageProducer.sendMessage("POST usuario MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return usuarioEntity;
    }

    public usuarioEntity updateusuario(usuarioRequest usuarioRequest, Long id) {

        Span span = tracer.buildSpan("UPDATE usuario/" + id).start();


        return usuarioRepository.findById(id).map(usuarioRequestObje -> {
            usuarioRequest.setId(id);
            usuarioEntity usuario = usuarioRepository.save(usuarioDTOTousuarioEntityMapper.usuarioDTOTousuarioEntityMapper(usuarioRequest));
            messageProducer.sendMessage("UPDATE usuario MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
            span.finish();
        return usuario;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteusuarioById(Long id) {
        Span span = tracer.buildSpan("DELETE usuario/" + id).start();

        usuarioRepository.findById(id).ifPresent(delete -> usuarioRepository.deleteById(id));

        messageProducer.sendMessage("DELETE usuario/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
    }
}