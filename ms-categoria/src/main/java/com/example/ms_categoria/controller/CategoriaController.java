package com.example.ms_categoria.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_categoria.dto.CategoriaRequestDTO;
import com.example.ms_categoria.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Slf4j
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public ResponseEntity<?> guardar(
        @Valid @RequestBody CategoriaRequestDTO dto){

        log.info("POST categoria");

        return ResponseEntity.status(HttpStatus.SC_CREATED)
        .body(service.guardar(dto));

    }

    @GetMapping
    public ResponseEntity<?> listar(){

        return ResponseEntity.ok(service.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(
        @PathVariable Long id){

        return ResponseEntity.ok(
        service.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody CategoriaRequestDTO dto){

        return ResponseEntity.ok(
        service.actualizar(id,dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){service.eliminar(id);

        return ResponseEntity.noContent().build();

    }

}