package com.pe.covid.core.cocovid.controller;

import com.pe.covid.core.cocovid.domain.videoEntity;
import com.pe.covid.core.cocovid.service.videoService;
import com.pe.covid.core.cocovid.exception.ExceptionResponse;
import com.pe.covid.core.cocovid.model.videoResponse;
import com.pe.covid.core.cocovid.model.videoRequest;
import com.pe.covid.core.cocovid.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("video")
@Slf4j
@Api(value = "videoController", produces = "application/json", tags = { "Controlador video" })
public class videoController {

    private final videoService videoService;

    @Autowired
    public videoController(videoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<videoEntity> getAllvideos() {
        return videoService.findAllvideos();
    }

    @ApiOperation(value = "Obtiene video por ID", tags = { "Controlador video" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video encontrada", response = videoEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<videoEntity> getvideoById(@PathVariable Long id) {
        return videoService.findvideoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra video", tags = { "Controlador video" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "video registrada", response = videoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<videoResponse> createvideo(@RequestBody @Validated videoRequest videoRequest) {
        videoService.savevideo(videoRequest);
        return new ResponseEntity<>(new videoResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza video", tags = { "Controlador video" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video actualizada", response = videoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<videoResponse> updatevideo(@PathVariable Long id, @RequestBody videoRequest videoRequest) throws Exception {
        videoService.updatevideo(videoRequest, id);
        return new ResponseEntity<>(new videoResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina video", tags = { "Controlador video" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video eliminada", response = videoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<videoResponse> deletevideo(@PathVariable Long id) {
        videoService.deletevideoById(id);
        return new ResponseEntity<>(new videoResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
