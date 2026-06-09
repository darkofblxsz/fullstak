package com.example.ms_pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_pedido.model.PedidoDetalle;

public interface PedidoDetalleRepository
extends JpaRepository<PedidoDetalle,Long>{

    List<PedidoDetalle> findByPedidoId(Long pedidoId);
}
