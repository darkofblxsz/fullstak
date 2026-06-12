package com.example.ms_cliente.controller;

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

import com.example.ms_cliente.dto.ClienteRequestDTO;
import com.example.ms_cliente.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<?> guardar(
        @Valid
        @RequestBody
        ClienteRequestDTO dto){log.info("POST cliente");

        return ResponseEntity
        .status(HttpStatus.SC_CREATED)
        .body(service.guardar(dto));

    }

    @GetMapping
    public ResponseEntity<?> listar(){

        log.info("GET clientes");

        return ResponseEntity.ok(
        service.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(
        @PathVariable Long id){

        return ResponseEntity.ok(
        service.buscarPorId(id));

            
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
        @PathVariable Long id,
        @Valid
        @RequestBody ClienteRequestDTO dto){

        return ResponseEntity.ok(
        service.actualizar(
        id,dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
        @PathVariable Long id){

        service.eliminar(id);

        return ResponseEntity.noContent()
        .build();

    }   

}