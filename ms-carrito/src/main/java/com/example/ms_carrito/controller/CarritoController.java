package com.example.ms_carrito.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ms_carrito.dto.CarritoItemRequestDTO;
import com.example.ms_carrito.service.CarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/carrito")
@Tag(name = "Carrito", description = "Operaciones del microservicio carrito")
@RequiredArgsConstructor
@Slf4j
public class CarritoController {
    private final CarritoService service;

    @Operation(summary = "Obtener informacion", description = "Endpoint documentado para obtener informacion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/{clienteId}")
    public ResponseEntity<?> obtener(@PathVariable Long clienteId){
    return ResponseEntity.ok(service.obtenerCarrito(clienteId));
    }

    @Operation(summary = "Agregar producto al carrito", description = "Endpoint documentado para agregar producto al carrito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @PostMapping("/{clienteId}")
    public ResponseEntity<?> agregar(@PathVariable Long clienteId,@Valid @RequestBody CarritoItemRequestDTO dto){

    return ResponseEntity.ok(
    service.agregarProducto(clienteId, dto));
    }

    @Operation(summary = "Eliminar registro", description = "Endpoint documentado para eliminar registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{clienteId}/producto/{productoId}")
    public ResponseEntity<?> eliminar(
    @PathVariable Long clienteId,
    @PathVariable Long productoId){

    service.eliminarProducto(clienteId, productoId);
    return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Limpiar carrito", description = "Endpoint documentado para limpiar carrito.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> limpiar(@PathVariable Long clienteId){

    service.limpiarCarrito(clienteId);
    return ResponseEntity.noContent().build();
    }
}
