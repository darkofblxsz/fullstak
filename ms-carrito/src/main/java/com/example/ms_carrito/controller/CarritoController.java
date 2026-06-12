package com.example.ms_carrito.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_carrito.dto.CarritoItemRequestDTO;
import com.example.ms_carrito.service.CarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
@Slf4j
public class CarritoController {
    private final CarritoService service;

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> obtener(@PathVariable Long clienteId){
    return ResponseEntity.ok(service.obtenerCarrito(clienteId));
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<?> agregar(@PathVariable Long clienteId,@Valid @RequestBody CarritoItemRequestDTO dto){

    return ResponseEntity.ok(
    service.agregarProducto(clienteId, dto));
    }

    @DeleteMapping("/{clienteId}/producto/{productoId}")
    public ResponseEntity<?> eliminar(
    @PathVariable Long clienteId,
    @PathVariable Long productoId){

    service.eliminarProducto(clienteId, productoId);
    return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> limpiar(@PathVariable Long clienteId){

    service.limpiarCarrito(clienteId);
    return ResponseEntity.noContent().build();
    }
}
