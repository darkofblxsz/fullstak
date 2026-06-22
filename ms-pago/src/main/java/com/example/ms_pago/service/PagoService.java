package com.example.ms_pago.service;

import com.example.ms_pago.model.Pago;
import com.example.ms_pago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {
    @Autowired
    private PagoRepository repository;

    public List<Pago> obtenerTodos() { return repository.findAll(); }

    public Pago obtenerPorId(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado")); }

    public Pago guardar(Pago pago) {
        if (pago.getEstado() == null || pago.getEstado().isBlank()) pago.setEstado("PAGADO");
        return repository.save(pago);
    }

    public Pago actualizar(Long id, Pago pagoActualizado) {
        Pago pago = obtenerPorId(id);
        pago.setPedidoId(pagoActualizado.getPedidoId());
        pago.setMetodoPago(pagoActualizado.getMetodoPago());
        pago.setMonto(pagoActualizado.getMonto());
        pago.setEstado(pagoActualizado.getEstado());
        return repository.save(pago);
    }

    public void eliminar(Long id) { repository.deleteById(id); }
}
