package com.example.ms_carrito.service;

import com.example.ms_carrito.dto.CarritoItemRequestDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;

public interface CarritoService {

    CarritoResponseDTO obtenerCarrito(Long clienteId);

    CarritoResponseDTO agregarProducto(Long clienteId, CarritoItemRequestDTO dto);

    void eliminarProducto(Long clienteId, Long productoId);

    void limpiarCarrito(Long clienteId);
}