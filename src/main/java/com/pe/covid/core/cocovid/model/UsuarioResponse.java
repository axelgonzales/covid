package com.pe.covid.core.cocovid.model;

import com.pe.covid.core.cocovid.domain.UsuarioEntity;

public class UsuarioResponse {

    private String message;
    private UsuarioEntity entity;

    
    public UsuarioEntity getEntity() {
		return entity;
	}

	public void setEntity(UsuarioEntity entity) {
		this.entity = entity;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

	public UsuarioResponse(String message) {
		super();
		this.message = message;
	}
	
	

	public UsuarioResponse(String message, UsuarioEntity entity) {
		super();
		this.message = message;
		this.entity = entity;
	}

	public UsuarioResponse() {
        super();
    }
}