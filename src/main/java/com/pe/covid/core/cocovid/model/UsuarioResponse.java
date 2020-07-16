package com.pe.covid.core.cocovid.model;

public class UsuarioResponse {

    private String message;

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

	public UsuarioResponse() {
        super();
    }
}