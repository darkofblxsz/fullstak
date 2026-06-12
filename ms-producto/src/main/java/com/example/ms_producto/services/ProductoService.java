package com.example.ms_producto.services;

import java.util.List;

import com.example.ms_producto.dto.ProductoRequestDTO;
import com.example.ms_producto.dto.ProductoResponseDTO;

public interface ProductoService {

    ProductoResponseDTO guardar(ProductoRequestDTO dto);

    List<ProductoResponseDTO> listar();

    ProductoResponseDTO buscar(Long id);

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

    void eliminar(Long id);
}