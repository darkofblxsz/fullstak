package com.example.ms_boleta.controller;

import com.example.ms_boleta.model.Boleta;
import com.example.ms_boleta.service.BoletaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boletas")
public class BoletaController {

    private final BoletaService boletaService;

    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    // LISTAR
    @GetMapping
    public List<Boleta> listar() {
        return boletaService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Optional<Boleta> buscar(@PathVariable Long id) {
        return boletaService.buscar(id);
    }

    // CREAR
    @PostMapping
    public Boleta guardar(@RequestBody Boleta boleta) {
        return boletaService.guardar(boleta);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {

        boletaService.eliminar(id);

        return "Boleta eliminada correctamente";
    }
}