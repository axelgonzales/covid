package com.pe.covid.core.cocovid.controller;

import com.pe.covid.core.cocovid.domain.UsuarioEntity;
import com.pe.covid.core.cocovid.service.UsuarioService;
import com.pe.covid.core.cocovid.exception.ExceptionResponse;
import com.pe.covid.core.cocovid.model.UsuarioResponse;
import com.pe.covid.core.cocovid.model.UsuarioRequest;
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
@RequestMapping("usuario")
@Slf4j
@Api(value = "usuarioController", produces = "application/json", tags = { "Controlador usuario" })
public class UsuarioController {

    private final UsuarioService UsuarioService;

    @Autowired
    public UsuarioController(UsuarioService UsuarioService) {
        this.UsuarioService = UsuarioService;
    }

    @GetMapping
    public List<UsuarioEntity> getAllusuarios() {
        return UsuarioService.findAllusuarios();
    }

    @ApiOperation(value = "Obtiene usuario por ID", tags = { "Controlador usuario" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "usuario encontrada", response = UsuarioEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioEntity> getusuarioById(@PathVariable Long id) {
        return UsuarioService.findusuarioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra usuario", tags = { "Controlador usuario" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "usuario registrada", response = UsuarioRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioResponse> createusuario(@RequestBody @Validated UsuarioRequest UsuarioRequest) {
        UsuarioService.saveusuario(UsuarioRequest);
        return new ResponseEntity<>(new UsuarioResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza usuario", tags = { "Controlador usuario" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "usuario actualizada", response = UsuarioRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioResponse> updateusuario(@PathVariable Long id, @RequestBody UsuarioRequest UsuarioRequest) throws Exception {
        UsuarioService.updateusuario(UsuarioRequest, id);
        return new ResponseEntity<>(new UsuarioResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina usuario", tags = { "Controlador usuario" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "usuario eliminada", response = UsuarioRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioResponse> deleteusuario(@PathVariable Long id) {
        UsuarioService.deleteusuarioById(id);
        return new ResponseEntity<>(new UsuarioResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
