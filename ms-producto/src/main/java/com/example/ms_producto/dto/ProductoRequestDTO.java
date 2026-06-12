package com.example.ms_producto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ProductoRequestDTO {
    @NotBlank
    @Size(min=3,max=100)
    private String nombre;

    @Size(max=200)
    private String descripcion;

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long proveedorId;
}
