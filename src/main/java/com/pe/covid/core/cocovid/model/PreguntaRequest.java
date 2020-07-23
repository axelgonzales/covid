package com.pe.covid.core.cocovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaRequest {
    private Long id;
    private String pregunta;
    private String respuesta;
}
