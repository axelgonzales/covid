package com.pe.covid.core.cocovid.model;

public class videoResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public videoResponse(String message) {
        super();
        this.message = message;
    }

    public videoResponse() {
        super();
    }
}