package com.example.ms_producto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Datos para crear o actualizar un producto de supermercado")
public class ProductoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100)
    @Schema(example = "Notebook Lenovo")
    private String nombre;

    @Size(max = 250)
    @Schema(example = "Notebook para oficina")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    @Schema(example = "450000")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Schema(example = "10")
    private Integer stock;

    @NotBlank(message = "La categoria es obligatoria")
    @Schema(example = "Tecnologia")
    private String categoria;

    @NotBlank(message = "La marca es obligatoria")
    @Schema(example = "Lenovo")
    private String marca;

    @Schema(example = "1")
    private Long categoriaId;

    @Schema(example = "1")
    private Long proveedorId;

    @Schema(example = "7800000000012")
    private String codigoBarras;
}
