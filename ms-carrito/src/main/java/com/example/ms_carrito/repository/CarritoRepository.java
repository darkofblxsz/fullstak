package com.example.ms_carrito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_carrito.modelo.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito,Long>{

    Optional<Carrito> findByClienteId(Long clienteId);
}