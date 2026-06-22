package com.example.ms_pedido.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PedidoRequestDTO {

    @NotNull
    private Long clienteId;

    @NotEmpty
    private List<PedidoItemDTO> items;
}
