package com.example.ms_carrito.service;

import org.springframework.stereotype.Service;

import com.example.ms_carrito.dto.CarritoItemRequestDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;

@Service
public class CarritoServiceImpl implements CarritoService {

    

    

    @Override
    public void eliminarProducto(Long clienteId, Long productoId) {

    }

    @Override
    public void limpiarCarrito(Long clienteId) {

    }

    @Override
    public CarritoResponseDTO obtenerCarrito(Long clienteId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCarrito'");
    }

    @Override
    public CarritoResponseDTO agregarProducto(Long clienteId, CarritoItemRequestDTO dto) {
        
        throw new UnsupportedOperationException("Unimplemented method 'agregarProducto'");
    }
}