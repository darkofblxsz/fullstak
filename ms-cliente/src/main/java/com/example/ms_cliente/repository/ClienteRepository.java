package com.example.ms_cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_cliente.model.Cliente;

@Repository
public interface ClienteRepository
extends JpaRepository<Cliente,Long>{

    Optional<Cliente> findByCorreo(String correo);

}