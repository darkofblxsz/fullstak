package com.example.ms_boleta.service;

import com.example.ms_boleta.model.Boleta;
import com.example.ms_boleta.repository.BoletaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletaService {

    private final BoletaRepository boletaRepository;

    public BoletaService(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    // LISTAR
    public List<Boleta> listar() {
        return boletaRepository.findAll();
    }

    // BUSCAR POR ID
    public Optional<Boleta> buscar(Long id) {
        return boletaRepository.findById(id);
    }

    // GUARDAR
    public Boleta guardar(Boleta boleta) {

        double iva = boleta.getTotal() * 0.19;
        double totalFinal = boleta.getTotal() + iva;

        boleta.setIva(iva);
        boleta.setTotalFinal(totalFinal);

        return boletaRepository.save(boleta);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        boletaRepository.deleteById(id);
    }
}