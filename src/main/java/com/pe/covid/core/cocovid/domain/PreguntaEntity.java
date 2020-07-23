package com.pe.covid.core.cocovid.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="preguntas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaEntity {

    @Id
    @SequenceGenerator(name = "pregunta_id_generator", sequenceName = "pregunta_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pregunta_id_generator")
    private Long id;


    private String pregunta;
    private String respuesta;
}
