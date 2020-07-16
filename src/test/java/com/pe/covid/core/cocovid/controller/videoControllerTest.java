package com.pe.covid.core.cocovid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.controller.VideoController;
import com.pe.covid.core.cocovid.model.VideoRequest;
import com.pe.covid.core.cocovid.service.VideoService;
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
@WebMvcTest(controllers = VideoController.class)
@ActiveProfiles("test")
class videoControllerTest {

    /*
    TODO: mock tracing

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private videoService videoService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<videoEntity> videoList;

    @BeforeEach
    void setUp() {
        this.videoList = new ArrayList<>();
        this.videoList.add(new videoEntity(1L, "video 1"));
        this.videoList.add(new videoEntity(2L, "video 2"));
        this.videoList.add(new videoEntity(3L, "video 3"));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFetchAllvideos() throws Exception {
        given(videoService.findAllvideos()).willReturn(this.videoList);

        this.mockMvc.perform(get("/video"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(videoList.size())));
    }

    @Test
    void shouldFindvideoById() throws Exception {
        Long videoId = 1L;
        videoEntity video = new videoEntity(videoId, "text 1");
        given(videoService.findvideoById(videoId)).willReturn(Optional.of(video));

        this.mockMvc.perform(get("/video/{id}", videoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(video.getText())))
        ;
    }

    @Test
    void shouldReturn404WhenFetchingNonExistingvideo() throws Exception {
        Long videoId = 1L;
        given(videoService.findvideoById(videoId)).willReturn(Optional.empty());

        this.mockMvc.perform(get("video/{id}", videoId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldCreateNewvideo() throws Exception {
        videoRequest videoRequest = new  videoRequest(5L, "new videoRequest");
        given(videoService.savevideo(videoRequest)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(post("/video")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdatevideo() throws Exception {
        Long videoId = 1L;
        videoRequest videoRequest = new videoRequest(videoId, "video updated");
        given(videoService.findvideoById(videoId)).willReturn(Optional.of(new videoEntity(videoId, "video updated")));

        this.mockMvc.perform(put("/video/{id}", videoRequest.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeletevideo() throws Exception {
        Long videoId = 1L;
        videoEntity video = new videoEntity(videoId, "Some text");
        given(videoService.findvideoById(videoId)).willReturn(Optional.of(video));
        doNothing().when(videoService).deletevideoById(video.getId());

        this.mockMvc.perform(delete("/video/{id}", video.getId()))
                .andExpect(status().isAccepted());
    }
     */
}
