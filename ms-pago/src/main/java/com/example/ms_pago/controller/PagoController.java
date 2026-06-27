package com.example.ms_pago.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.ms_pago.model.Pago;
import com.example.ms_pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/pagos", "/api/pagos"})
@Tag(name = "Pagos", description = "Pagos de pedidos del supermercado")
public class PagoController {
    @Autowired
    private PagoService service;

    @Operation(summary = "Listar pagos")
    @GetMapping
    public ResponseEntity<?> listar() { return ResponseEntity.ok(service.obtenerTodos()); }

    @Operation(summary = "Buscar pago por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) { return ResponseEntity.ok(service.obtenerPorId(id)); }

    @Operation(summary = "Registrar pago")
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Pago pago) { return ResponseEntity.ok(service.guardar(pago)); }

    @Operation(summary = "Actualizar pago")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pago pago) { return ResponseEntity.ok(service.actualizar(id, pago)); }

    @Operation(summary = "Eliminar pago")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) { service.eliminar(id); return ResponseEntity.ok().build(); }
}
