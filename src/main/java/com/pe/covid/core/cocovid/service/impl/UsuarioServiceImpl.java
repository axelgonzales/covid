package com.pe.covid.core.cocovid.service.impl;

import com.pe.covid.core.cocovid.domain.UsuarioEntity;
import com.pe.covid.core.cocovid.repository.UsuarioRepository;
import com.pe.covid.core.cocovid.model.UsuarioRequest;
import com.pe.covid.core.cocovid.service.impl.mapper.UsuarioDTOToUsuarioEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.covid.core.cocovid.service.UsuarioService;
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
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository UsuarioRepository;
    private UsuarioDTOToUsuarioEntityMapper UsuarioDTOToUsuarioEntityMapper = new UsuarioDTOToUsuarioEntityMapper();
    private Tracer tracer;
    private MessageProducer messageProducer;
    private TraceAndSpan traceAndSpan = new TraceAndSpan();

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository UsuarioRepository,  Tracer tracer, MessageProducer messageProducer) {
        this.UsuarioRepository = UsuarioRepository;
        this.tracer = tracer;
        this.messageProducer = messageProducer;
    }

    public List<UsuarioEntity> findAllusuarios() {
        Span span = tracer.buildSpan("GET ALL usuario").start();

        List<UsuarioEntity> listusuario = UsuarioRepository.findAll();

        messageProducer.sendMessage("GET ALL usuario MESSAGE TEST" + "-" +  traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return listusuario;
    }

    public Optional<UsuarioEntity> findusuarioById(Long id) {
        Span span = tracer.buildSpan("GET usuario/" + id).start();

        Optional<UsuarioEntity> optionalusuario = UsuarioRepository.findById(id);

        messageProducer.sendMessage("GET usuario/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return optionalusuario;
    }

    public UsuarioEntity saveusuario(UsuarioRequest UsuarioRequest) {
        Span span = tracer.buildSpan("POST usuario").start();

        UsuarioEntity UsuarioEntity = UsuarioRepository.save(UsuarioDTOToUsuarioEntityMapper.usuarioDTOTousuarioEntityMapper(UsuarioRequest));

        messageProducer.sendMessage("POST usuario MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
        return UsuarioEntity;
    }

    public UsuarioEntity updateusuario(UsuarioRequest UsuarioRequest, Long id) {

        Span span = tracer.buildSpan("UPDATE usuario/" + id).start();


        return UsuarioRepository.findById(id).map(usuarioRequestObje -> {
            UsuarioRequest.setId(id);
            UsuarioEntity usuario = UsuarioRepository.save(UsuarioDTOToUsuarioEntityMapper.usuarioDTOTousuarioEntityMapper(UsuarioRequest));
            messageProducer.sendMessage("UPDATE usuario MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
            span.finish();
        return usuario;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteusuarioById(Long id) {
        Span span = tracer.buildSpan("DELETE usuario/" + id).start();

        UsuarioRepository.findById(id).ifPresent(delete -> UsuarioRepository.deleteById(id));

        messageProducer.sendMessage("DELETE usuario/ MESSAGE TEST" + "-" + traceAndSpan.getTraceId(span) + "-" + traceAndSpan.getSpanId(span));
        span.finish();
    }
}