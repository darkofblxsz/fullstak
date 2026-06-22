package com.example.ms_proveedor.service;

import java.util.List;

import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorRequestDTO;
import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorResponseDTO;

public interface ProveedorService {

    ProveedorResponseDTO guardar(ProveedorRequestDTO dto);
    List<ProveedorResponseDTO> listar();
    ProveedorResponseDTO buscar(Long id);
    ProveedorResponseDTO actualizar(Long id, ProveedorRequestDTO dto);
    void eliminar(Long id);
}