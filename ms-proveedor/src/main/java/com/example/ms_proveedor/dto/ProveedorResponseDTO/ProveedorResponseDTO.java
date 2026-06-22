package com.example.ms_proveedor.dto.ProveedorResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ProveedorResponseDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
}