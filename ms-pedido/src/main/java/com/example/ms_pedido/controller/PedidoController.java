package com.example.ms_pedido.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ms_pedido.dto.PedidoRequestDTO;
import com.example.ms_pedido.service.PedidoService;
import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Operaciones del microservicio pedidos")
@RequiredArgsConstructor
@Slf4j
public class PedidoController {

    private final PedidoService service;

    @Operation(summary = "Crear nuevo registro", description = "Endpoint documentado para crear nuevo registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PedidoRequestDTO dto){
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(service.crearPedido(dto));
    }

    @Operation(summary = "Obtener informacion", description = "Endpoint documentado para obtener informacion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id){
    return ResponseEntity.ok(service.obtener(id));
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
}
