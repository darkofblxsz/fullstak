package com.example.ms_cliente.service;

import java.util.List;

import com.example.ms_cliente.dto.ClienteRequestDTO;
import com.example.ms_cliente.dto.ClienteResposeDTO;

public interface ClienteService {

    ClienteResposeDTO guardar(
            ClienteRequestDTO dto);

    List<ClienteResposeDTO> listar();

    ClienteResposeDTO buscarPorId(
            Long id);

    ClienteResposeDTO actualizar(
            Long id,
            ClienteRequestDTO dto);

    void eliminar(Long id);

}