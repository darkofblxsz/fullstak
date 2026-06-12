package com.example.ms_cliente.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteResposeDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;

}
