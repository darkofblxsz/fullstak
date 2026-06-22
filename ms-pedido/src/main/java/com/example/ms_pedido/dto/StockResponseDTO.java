package com.example.ms_pedido.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponseDTO {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Integer cantidadDisponible;

    public Integer getCantidadDisponible() {
        return cantidadDisponible != null ? cantidadDisponible : cantidad;
    }
}
