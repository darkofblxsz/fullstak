package com.example.ms_envio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_envio.dto.EnvioRequestDTO;
import com.example.ms_envio.services.EnvioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
@Slf4j
public class EnvioController {

    private final EnvioService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody EnvioRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> listarCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id,
                                              @RequestParam String estado){

        return ResponseEntity.ok(
                service.actualizarEstado(id, estado));
    }
}
