package com.example.ms_categoria.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriaResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;

}