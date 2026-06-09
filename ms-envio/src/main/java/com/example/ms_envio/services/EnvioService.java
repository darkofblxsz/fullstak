package com.example.ms_envio.services;

import java.util.List;

import com.example.ms_envio.dto.EnvioRequestDTO;
import com.example.ms_envio.dto.EnvioResponseDTO;

public interface EnvioService {

    EnvioResponseDTO crear(EnvioRequestDTO dto);

    List<EnvioResponseDTO> listar();

    List<EnvioResponseDTO> listarPorCliente(Long clienteId);

    EnvioResponseDTO actualizarEstado(Long id, String estado);
}