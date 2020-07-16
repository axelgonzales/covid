package com.pe.covid.core.cocovid.repository;

import com.pe.covid.core.cocovid.domain.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}