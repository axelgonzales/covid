package com.pe.covid.core.cocovid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.covid.core.cocovid.domain.usuarioEntity;
import com.pe.covid.core.cocovid.controller.usuarioController;
import com.pe.covid.core.cocovid.model.usuarioRequest;
import com.pe.covid.core.cocovid.service.usuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = usuarioController.class)
@ActiveProfiles("test")
class usuarioControllerTest {

    /*
    TODO: mock tracing

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private usuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<usuarioEntity> usuarioList;

    @BeforeEach
    void setUp() {
        this.usuarioList = new ArrayList<>();
        this.usuarioList.add(new usuarioEntity(1L, "usuario 1"));
        this.usuarioList.add(new usuarioEntity(2L, "usuario 2"));
        this.usuarioList.add(new usuarioEntity(3L, "usuario 3"));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFetchAllusuarios() throws Exception {
        given(usuarioService.findAllusuarios()).willReturn(this.usuarioList);

        this.mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(usuarioList.size())));
    }

    @Test
    void shouldFindusuarioById() throws Exception {
        Long usuarioId = 1L;
        usuarioEntity usuario = new usuarioEntity(usuarioId, "text 1");
        given(usuarioService.findusuarioById(usuarioId)).willReturn(Optional.of(usuario));

        this.mockMvc.perform(get("/usuario/{id}", usuarioId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(usuario.getText())))
        ;
    }

    @Test
    void shouldReturn404WhenFetchingNonExistingusuario() throws Exception {
        Long usuarioId = 1L;
        given(usuarioService.findusuarioById(usuarioId)).willReturn(Optional.empty());

        this.mockMvc.perform(get("usuario/{id}", usuarioId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldCreateNewusuario() throws Exception {
        usuarioRequest usuarioRequest = new  usuarioRequest(5L, "new usuarioRequest");
        given(usuarioService.saveusuario(usuarioRequest)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateusuario() throws Exception {
        Long usuarioId = 1L;
        usuarioRequest usuarioRequest = new usuarioRequest(usuarioId, "usuario updated");
        given(usuarioService.findusuarioById(usuarioId)).willReturn(Optional.of(new usuarioEntity(usuarioId, "usuario updated")));

        this.mockMvc.perform(put("/usuario/{id}", usuarioRequest.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteusuario() throws Exception {
        Long usuarioId = 1L;
        usuarioEntity usuario = new usuarioEntity(usuarioId, "Some text");
        given(usuarioService.findusuarioById(usuarioId)).willReturn(Optional.of(usuario));
        doNothing().when(usuarioService).deleteusuarioById(usuario.getId());

        this.mockMvc.perform(delete("/usuario/{id}", usuario.getId()))
                .andExpect(status().isAccepted());
    }
     */
}
