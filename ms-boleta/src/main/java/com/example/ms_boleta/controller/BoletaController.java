package com.example.ms_boleta.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.ms_boleta.model.Boleta;
import com.example.ms_boleta.service.BoletaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/boletas", "/api/boletas"})
@Tag(name = "Boletas", description = "Boletas y comprobantes de venta del supermercado")
public class BoletaController {
    private final BoletaService boletaService;

    public BoletaController(BoletaService boletaService) { this.boletaService = boletaService; }

    @Operation(summary = "Listar boletas")
    @GetMapping
    public List<Boleta> listar() { return boletaService.listar(); }

    @Operation(summary = "Buscar boleta por id")
    @GetMapping("/{id}")
    public Optional<Boleta> buscar(@PathVariable Long id) { return boletaService.buscar(id); }

    @Operation(summary = "Crear boleta con calculo de IVA")
    @PostMapping
    public Boleta guardar(@RequestBody Boleta boleta) { return boletaService.guardar(boleta); }

    @Operation(summary = "Eliminar boleta")
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) { boletaService.eliminar(id); return "Boleta eliminada correctamente"; }
}
