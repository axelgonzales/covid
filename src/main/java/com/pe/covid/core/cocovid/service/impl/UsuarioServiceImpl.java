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


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository UsuarioRepository;
    private UsuarioDTOToUsuarioEntityMapper UsuarioDTOToUsuarioEntityMapper = new UsuarioDTOToUsuarioEntityMapper();


    @Autowired
    public UsuarioServiceImpl(UsuarioRepository UsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
     
    }

    public List<UsuarioEntity> findAllusuarios() {

        List<UsuarioEntity> listusuario = UsuarioRepository.findAll();

        return listusuario;
    }

    public Optional<UsuarioEntity> findusuarioById(Long id) {

        Optional<UsuarioEntity> optionalusuario = UsuarioRepository.findById(id);


        return optionalusuario;
    }

    public UsuarioEntity saveusuario(UsuarioRequest UsuarioRequest) {

        UsuarioEntity UsuarioEntity = UsuarioRepository.save(UsuarioDTOToUsuarioEntityMapper.usuarioDTOTousuarioEntityMapper(UsuarioRequest));

        return UsuarioEntity;
    }

    public UsuarioEntity updateusuario(UsuarioRequest UsuarioRequest, Long id) {

        return UsuarioRepository.findById(id).map(usuarioRequestObje -> {
            UsuarioRequest.setId(id);
            UsuarioEntity usuario = UsuarioRepository.save(UsuarioDTOToUsuarioEntityMapper.usuarioDTOTousuarioEntityMapper(UsuarioRequest));

        return usuario;

        })
        .orElseThrow(() -> new ModelNotFoundException(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteusuarioById(Long id) {

        UsuarioRepository.findById(id).ifPresent(delete -> UsuarioRepository.deleteById(id));

    }
}