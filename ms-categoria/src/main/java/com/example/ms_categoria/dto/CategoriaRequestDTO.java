package com.example.ms_categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDTO {

    @NotBlank(message="Nombre obligatorio")
    @Size(min=3,max=50)
    private String nombre;

    @Size(max=150)
    private String descripcion;

}