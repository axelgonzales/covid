package com.pe.covid.core.cocovid.model;

public class usuarioResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public usuarioResponse(String message) {
        super();
        this.message = message;
    }

    public usuarioResponse() {
        super();
    }
}