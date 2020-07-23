package com.pe.covid.core.cocovid.service;

import com.pe.covid.core.cocovid.domain.PreguntaEntity;
import com.pe.covid.core.cocovid.model.PreguntaRequest;

import java.util.List;
import java.util.Optional;

public interface PreguntaService {

    public List<PreguntaEntity> findAllPreguntas();

    public Optional<PreguntaEntity> findPreguntaById(Long id);

    public PreguntaEntity savePregunta(PreguntaRequest preguntaRequest);

    public PreguntaEntity updatePregunta(PreguntaRequest preguntaRequest, Long id);

    public void deletePreguntaById(Long id);
}
