package com.example.ms_pago.controller;

import com.example.ms_pago.model.Pago;
import com.example.ms_pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService service;

    @GetMapping
    public List<Pago> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Pago> obtenerPorId(@PathVariable @NonNull Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Pago guardar(@RequestBody @NonNull Pago pago) {
        return service.guardar(pago);
    }

    @PutMapping("/{id}")
    public Pago actualizar(@PathVariable @NonNull Long id,
                           @RequestBody Pago pago) {

        return service.actualizar(id, pago);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @NonNull Long id) {
        service.eliminar(id);
    }
}