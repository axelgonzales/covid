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
@Table(name="videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity {

    @Id
    @SequenceGenerator(name = "video_id_generator", sequenceName = "video_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "video_id_generator")
    private Long id;

    private String description;
    private String ruta;
    private String path;
    private String title;
    private Integer category;
}
