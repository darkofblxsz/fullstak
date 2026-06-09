package com.example.ms_envio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_envio.modelo.Envio;

public interface EnvioRepository extends JpaRepository<Envio,Long>{

    List<Envio> findByClienteId(Long clienteId);
}
