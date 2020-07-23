package com.pe.covid.core.cocovid.controller;

import com.pe.covid.core.cocovid.domain.PreguntaEntity;
import com.pe.covid.core.cocovid.service.PreguntaService;
import com.pe.covid.core.cocovid.exception.ExceptionResponse;
import com.pe.covid.core.cocovid.model.PreguntaResponse;
import com.pe.covid.core.cocovid.model.PreguntaRequest;
import com.pe.covid.core.cocovid.constant.Constant;
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
@RequestMapping("v1/pregunta")
@Api(value = "PreguntaController", produces = "application/json", tags = { "Controlador Pregunta" })
public class PreguntaController {

    private final PreguntaService preguntaService;

    @Autowired
    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping
    public List<PreguntaEntity> getAllPreguntas() {
        return preguntaService.findAllPreguntas();
    }

    @ApiOperation(value = "Obtiene Pregunta por ID", tags = { "Controlador Pregunta" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pregunta encontrada", response = PreguntaEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PreguntaEntity> getPreguntaById(@PathVariable Long id) {
        return preguntaService.findPreguntaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra Pregunta", tags = { "Controlador Pregunta" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pregunta registrada", response = PreguntaRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PreguntaResponse> createPregunta(@RequestBody @Validated PreguntaRequest preguntaRequest) {
        preguntaService.savePregunta(preguntaRequest);
        return new ResponseEntity<>(new PreguntaResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza Pregunta", tags = { "Controlador Pregunta" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pregunta actualizada", response = PreguntaRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PreguntaResponse> updatePregunta(@PathVariable Long id, @RequestBody PreguntaRequest preguntaRequest) throws Exception {
        preguntaService.updatePregunta(preguntaRequest, id);
        return new ResponseEntity<>(new PreguntaResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina Pregunta", tags = { "Controlador Pregunta" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pregunta eliminada", response = PreguntaRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<PreguntaResponse> deletePregunta(@PathVariable Long id) {
        preguntaService.deletePreguntaById(id);
        return new ResponseEntity<>(new PreguntaResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
