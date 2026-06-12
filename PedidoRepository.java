package com.example.ms_pedido.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class PedidoDetalleDTO {

    private Long productoId;
    private Integer cantidad;
    private Double precio;
}
