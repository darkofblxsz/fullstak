package com.example.ms_pedido.controller;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_pedido.dto.PedidoRequestDTO;
import com.example.ms_pedido.service.PedidoService;
import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Slf4j
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PedidoRequestDTO dto){
        return ResponseEntity
        .status(HttpStatus.SC_CREATED)
        .body(service.crearPedido(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
    return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    public ResponseEntity<?> listar(){
    return ResponseEntity.ok(service.listar());
    }
}