package com.pe.covid.core.cocovid.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class videoEntity {

    @Id
    @SequenceGenerator(name = "video_id_generator", sequenceName = "video_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "video_id_generator")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Text cannot be empty")
    private String text;
}
