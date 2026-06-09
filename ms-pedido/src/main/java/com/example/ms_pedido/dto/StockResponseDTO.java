package com.example.ms_pedido.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockResponseDTO {
    private Long productoId;
    private Integer cantidadDisponible;
}