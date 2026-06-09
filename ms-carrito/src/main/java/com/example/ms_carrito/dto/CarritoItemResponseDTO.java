package com.example.ms_carrito.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Builder
public class CarritoItemResponseDTO {
    private Long productoId;
    private Integer cantidad;
}
