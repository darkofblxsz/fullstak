package com.example.ms_categoria.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ms_categoria.dto.CategoriaRequestDTO;
import com.example.ms_categoria.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Operaciones del microservicio categorias")
@RequiredArgsConstructor
@Slf4j
public class CategoriaController {

    private final CategoriaService service;

    @Operation(summary = "Crear nuevo registro", description = "Endpoint documentado para crear nuevo registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @PostMapping
    public ResponseEntity<?> guardar(
        @Valid @RequestBody CategoriaRequestDTO dto){

        log.info("POST categoria");

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(service.guardar(dto));

    }

    @Operation(summary = "Listar registros", description = "Endpoint documentado para listar registros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok(service.listar());

    }

    @Operation(summary = "Buscar registro por id", description = "Endpoint documentado para buscar registro por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(
        @PathVariable Long id){

        return ResponseEntity.ok(
        service.buscarPorId(id));

    }

    @Operation(summary = "Actualizar registro", description = "Endpoint documentado para actualizar registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody CategoriaRequestDTO dto){

        return ResponseEntity.ok(
        service.actualizar(id,dto));

    }

    @Operation(summary = "Eliminar registro", description = "Endpoint documentado para eliminar registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){service.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}
