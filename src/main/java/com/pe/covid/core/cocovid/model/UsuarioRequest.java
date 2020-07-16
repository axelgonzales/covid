package com.pe.covid.core.cocovid.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
	
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String cellphone;
    private Integer gender;
    private String email;
    private Date birthDate;
    private String facebook;
}
