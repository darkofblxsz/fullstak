package com.example.ms_categoria.service;

import java.util.List;

import com.example.ms_categoria.dto.CategoriaRequestDTO;
import com.example.ms_categoria.dto.CategoriaResponseDTO;

public interface CategoriaService {

    CategoriaResponseDTO guardar(CategoriaRequestDTO dto);

    List<CategoriaResponseDTO> listar();

    CategoriaResponseDTO buscarPorId(Long id);

    CategoriaResponseDTO actualizar(Long id,CategoriaRequestDTO dto);

    void eliminar(Long id);

}