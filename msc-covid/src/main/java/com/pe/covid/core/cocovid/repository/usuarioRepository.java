package com.pe.covid.core.cocovid.repository;

import com.pe.covid.core.cocovid.domain.usuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioEntity, Long> {
}
