package com.example.ms_envio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ms_envio.dto.EnvioRequestDTO;
import com.example.ms_envio.services.EnvioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/envios")
@Tag(name = "Envios", description = "Operaciones del microservicio envios")
@RequiredArgsConstructor
@Slf4j
public class EnvioController {

    private final EnvioService service;

    @Operation(summary = "Crear nuevo registro", description = "Endpoint documentado para crear nuevo registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody EnvioRequestDTO dto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crear(dto));
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

    @Operation(summary = "Listar envios por cliente", description = "Endpoint documentado para listar envios por cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> listarCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @Operation(summary = "Actualizar estado del envio", description = "Endpoint documentado para actualizar estado del envio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id,
                                              @RequestParam String estado){

        return ResponseEntity.ok(
                service.actualizarEstado(id, estado));
    }
}
