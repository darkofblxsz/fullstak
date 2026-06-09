package com.example.ms_envio.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnvioResponseDTO {
    
    private Long id;
    private Long pedidoId;
    private Long clienteId;
    private String direccion;
    private String estado;
}
