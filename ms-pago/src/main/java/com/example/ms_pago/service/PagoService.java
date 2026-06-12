package com.example.ms_pago.service;

import com.example.ms_pago.model.Pago;
import com.example.ms_pago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository repository;

    public List<Pago> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Pago> obtenerPorId(@NonNull Long id) {
        return repository.findById(id);
    }

    public Pago guardar(@NonNull Pago pago) {
        return repository.save(pago);
    }

    public Pago actualizar(@NonNull Long id, Pago pagoActualizado) {

        return repository.findById(id).map(pago -> {

            pago.setPedidoId(pagoActualizado.getPedidoId());
            pago.setMetodoPago(pagoActualizado.getMetodoPago());
            pago.setMonto(pagoActualizado.getMonto());
            pago.setEstado(pagoActualizado.getEstado());

            return repository.save(pago);

        }).orElse(null);
    }

    public void eliminar(@NonNull Long id) {
        repository.deleteById(id);
    }
}