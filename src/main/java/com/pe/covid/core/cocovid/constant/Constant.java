package com.pe.covid.core.cocovid.constant;

public final class Constant {

    private Constant(){
        throw new IllegalStateException("Constant class");
    }

    public static final String REG_INS_ACCEPTED = "EL PROCESO DE REGISTRO SE REALIZO DE MANERA SATISFACTORIA";
    public static final String REG_ACT_ACCEPTED = "EL PROCESO DE ACTUALIZACIÓN SE REALIZO DE MANERA SATISFACTORIA";
    public static final String REG_ACT_NOT_ACCEPTED = "EL PROCESO DE ACTUALIZACIÓN SE REALIZO DE MANERA SATISFACTORIA";
    public static final String REG_ELI_NOT_OK = "EL PROCESO DE ELIMINACIÓN NO SE REALIZO DE MANERA SATISFACTORIA";
    public static final String REG_ELI_OK = "EL PROCESO DE ELIMINACIÓN SE REALIZO DE MANERA SATISFACTORIA";
    public static final String RELACION_NOT_FOUND = "NO SE LOGRO ENCONTRAR LA RELACIÓN";
    public static final String PERSONA_NOT_FOUND = "NO SE LOGRO ENCONTRAR A LA PERSONA";
    public static final Integer EST_REG_DESACTIVO = 0;
    public static final Integer EST_REG_ACTIVA = 1;

}