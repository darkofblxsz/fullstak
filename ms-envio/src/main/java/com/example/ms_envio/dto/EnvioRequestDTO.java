package com.example.ms_envio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class EnvioRequestDTO {
    @NotNull
    private Long pedidoId;

    @NotNull
    private Long clienteId;

    @NotBlank
    private String direccion;
}
