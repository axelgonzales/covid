package com.pe.covid.core.cocovid.repository;

import com.pe.covid.core.cocovid.domain.videoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface videoRepository extends JpaRepository<videoEntity, Long> {
}
