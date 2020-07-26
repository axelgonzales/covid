package com.pe.covid.core.cocovid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {
	
    private Long id;
    private String description;
    private String imagen;
    private String video;
    private String title;
    private Integer category;
}
