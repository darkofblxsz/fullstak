package com.example.ms_carrito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_carrito.modelo.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem,Long>{

    List<CarritoItem>findByCarritoId(Long carritoId);
}