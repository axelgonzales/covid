package com.pe.covid.core.cocovid.service.impl;

import com.pe.covid.core.cocovid.domain.PreguntaEntity;
import com.pe.covid.core.cocovid.repository.PreguntaRepository;
import com.pe.covid.core.cocovid.model.PreguntaRequest;
import com.pe.covid.core.cocovid.service.impl.mapper.PreguntaDTOToPreguntaEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pe.covid.core.cocovid.service.PreguntaService;
import com.pe.covid.core.cocovid.exception.ModelNotFoundException;
import com.pe.covid.core.cocovid.constant.Constant;


import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PreguntaServiceImpl implements PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private PreguntaDTOToPreguntaEntityMapper preguntaDTOToPreguntaEntityMapper = new PreguntaDTOToPreguntaEntityMapper();


    @Autowired
    public PreguntaServiceImpl(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    public List<PreguntaEntity> findAllPreguntas() {

        List<PreguntaEntity> listPregunta = preguntaRepository.findAll();

        log.info("GET ALL Pregunta MESSAGE TEST" );
        return listPregunta;
    }

    public Optional<PreguntaEntity> findPreguntaById(Long id) {

        Optional<PreguntaEntity> optionalPregunta = preguntaRepository.findById(id);

        log.info("GET Pregunta/ MESSAGE TEST" );
        
        return optionalPregunta;
    }

    public PreguntaEntity savePregunta(PreguntaRequest preguntaRequest) {
    	preguntaRequest.setRespuestaResume(preguntaRequest.getRespuesta().substring(0,100));
        PreguntaEntity preguntaEntity = preguntaRepository.save(preguntaDTOToPreguntaEntityMapper.preguntaDTOToPreguntaEntityMapper(preguntaRequest));
        
        
        log.info("POST Pregunta MESSAGE TEST" );
        
        return preguntaEntity;
    }

    public PreguntaEntity updatePregunta(PreguntaRequest preguntaRequest, Long id) {

    	preguntaRequest.setRespuestaResume(preguntaRequest.getRespuesta().substring(0,100));

        return preguntaRepository.findById(id).map(preguntaRequestObje -> {
            preguntaRequest.setId(id);
            PreguntaEntity pregunta = preguntaRepository.save(preguntaDTOToPreguntaEntityMapper.preguntaDTOToPreguntaEntityMapper(preguntaRequest));
            log.info("UPDATE Pregunta MESSAGE TEST" );
            
        return pregunta;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deletePreguntaById(Long id) {

        preguntaRepository.findById(id).ifPresent(delete -> preguntaRepository.deleteById(id));

        log.info("DELETE Pregunta/ MESSAGE TEST" );
        
    }
}