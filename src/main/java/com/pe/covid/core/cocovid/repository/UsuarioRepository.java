package com.pe.covid.core.cocovid.repository;

import com.pe.covid.core.cocovid.domain.UsuarioEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	public List<UsuarioEntity> findByUsernameAndPassword(String username, String password);
}
