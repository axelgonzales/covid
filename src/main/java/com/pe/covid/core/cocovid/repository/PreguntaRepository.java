package com.pe.covid.core.cocovid.repository;

import com.pe.covid.core.cocovid.domain.PreguntaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<PreguntaEntity, Long> {
}
