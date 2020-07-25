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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pe.covid.core.cocovid.constant.Constant;
import com.pe.covid.core.cocovid.domain.UsuarioEntity;
import com.pe.covid.core.cocovid.exception.ExceptionResponse;
import com.pe.covid.core.cocovid.model.UsuarioRequest;
import com.pe.covid.core.cocovid.model.UsuarioResponse;
import com.pe.covid.core.cocovid.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/usuario")
@Api(value = "usuarioController", produces = "application/json", tags = { "Controlador usuario" })
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioEntity> getAllusuarios() {
        return usuarioService.findAllusuarios();
    }

    @ApiOperation(value = "Obtiene usuario por ID", tags = { "Controlador usuario" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "usuario encontrada", response = UsuarioEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioEntity> getusuarioById(@PathVariable Long id) {
        return usuarioService.findusuarioById(id)
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
        return new ResponseEntity<>(new UsuarioResponse(Constant.REG_INS_ACCEPTED,usuarioService.saveusuario(UsuarioRequest)), HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Login usuario", tags = { "Controlador usuario" })
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "usuario registrada", response = UsuarioRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioEntity> login(@RequestParam String username, @RequestParam String password) {
        ;
        return new ResponseEntity<>(usuarioService.login(username,password), HttpStatus.OK);
    }

    @ApiOperation(value = "Actualiza usuario", tags = { "Controlador usuario" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "usuario actualizada", response = UsuarioRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioResponse> updateusuario(@PathVariable Long id, @RequestBody UsuarioRequest UsuarioRequest) throws Exception {
        usuarioService.updateusuario(UsuarioRequest, id);
        return new ResponseEntity<>(new UsuarioResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Elimina usuario", tags = { "Controlador usuario" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "usuario eliminada", response = UsuarioRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UsuarioResponse> deleteusuario(@PathVariable Long id) {
        usuarioService.deleteusuarioById(id);
        return new ResponseEntity<>(new UsuarioResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
