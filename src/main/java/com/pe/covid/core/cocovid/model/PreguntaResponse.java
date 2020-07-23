package com.pe.covid.core.cocovid.model;

public class PreguntaResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PreguntaResponse(String message) {
        super();
        this.message = message;
    }

    public PreguntaResponse() {
        super();
    }
}