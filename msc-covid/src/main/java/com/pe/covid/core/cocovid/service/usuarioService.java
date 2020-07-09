package com.pe.covid.core.cocovid.service;

import com.pe.covid.core.cocovid.domain.usuarioEntity;
import com.pe.covid.core.cocovid.model.usuarioRequest;

import java.util.List;
import java.util.Optional;

public interface usuarioService {

    public List<usuarioEntity> findAllusuarios();

    public Optional<usuarioEntity> findusuarioById(Long id);

    public usuarioEntity saveusuario(usuarioRequest usuarioRequest);

    public usuarioEntity updateusuario(usuarioRequest usuarioRequest, Long id);

    public void deleteusuarioById(Long id);
}
