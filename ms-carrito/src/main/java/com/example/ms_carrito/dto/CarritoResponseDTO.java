package com.example.ms_carrito.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Builder
public class CarritoResponseDTO {
     private Long carritoId;
    private Long clienteId;
    private List<CarritoItemResponseDTO> items;
}

