package com.example.ms_pedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoItemDTO {

    @NotNull
    private Long productoId;

    @Min(1)
    private Integer cantidad;
}
