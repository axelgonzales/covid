package com.pe.covid.core.cocovid.service;

import com.pe.covid.core.cocovid.domain.UsuarioEntity;
import com.pe.covid.core.cocovid.model.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<UsuarioEntity> findAllusuarios();

    public Optional<UsuarioEntity> findusuarioById(Long id);

    public UsuarioEntity saveusuario(UsuarioRequest UsuarioRequest);

    public UsuarioEntity updateusuario(UsuarioRequest UsuarioRequest, Long id);

    public void deleteusuarioById(Long id);
}
