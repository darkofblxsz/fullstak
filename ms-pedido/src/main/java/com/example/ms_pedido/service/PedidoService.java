package com.example.ms_pedido.service;

import java.util.List;

import com.example.ms_pedido.dto.PedidoRequestDTO;
import com.example.ms_pedido.dto.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO crearPedido(PedidoRequestDTO dto);

    PedidoResponseDTO obtener(Long id);

    List<PedidoResponseDTO> listar();

}