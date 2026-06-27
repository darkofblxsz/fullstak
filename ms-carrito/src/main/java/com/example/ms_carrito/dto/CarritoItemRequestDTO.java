package com.example.ms_carrito.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class CarritoItemRequestDTO {
    @NotNull
    private Long productoId;

    @NotNull
    @Min(1)
    private Integer cantidad;
}
