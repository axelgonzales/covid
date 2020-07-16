package com.pe.covid.core.cocovid.controller;

import java.util.List;

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

import com.pe.covid.core.cocovid.constant.Constant;
import com.pe.covid.core.cocovid.domain.VideoEntity;
import com.pe.covid.core.cocovid.exception.ExceptionResponse;
import com.pe.covid.core.cocovid.model.VideoRequest;
import com.pe.covid.core.cocovid.model.VideoResponse;
import com.pe.covid.core.cocovid.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/video")
@Api(value = "videoController", produces = "application/json", tags = { "Controlador video" })
public class VideoController {

    private final VideoService VideoService;

    @Autowired
    public VideoController(VideoService VideoService) {
        this.VideoService = VideoService;
    }

    @GetMapping
    public List<VideoEntity> getAllvideos() {
        return VideoService.findAllvideos();
    }

    @ApiOperation(value = "Obtiene video por ID", tags = { "Controlador video" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video encontrada", response = VideoEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoEntity> getvideoById(@PathVariable Long id) {
        return VideoService.findvideoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra video", tags = { "Controlador video" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "video registrada", response = VideoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoResponse> createvideo(@RequestBody @Validated VideoRequest VideoRequest) {
        VideoService.savevideo(VideoRequest);
        return new ResponseEntity<>(new VideoResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza video", tags = { "Controlador video" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video actualizada", response = VideoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoResponse> updatevideo(@PathVariable Long id, @RequestBody VideoRequest VideoRequest) throws Exception {
        VideoService.updatevideo(VideoRequest, id);
        return new ResponseEntity<>(new VideoResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina video", tags = { "Controlador video" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video eliminada", response = VideoRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<VideoResponse> deletevideo(@PathVariable Long id) {
        VideoService.deletevideoById(id);
        return new ResponseEntity<>(new VideoResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
