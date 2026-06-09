package com.example.ms_cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank(message="Nombre obligatorio")
    @Size(min=3,max=50)
    private String nombre;

    @NotBlank(message="Apellido obligatorio")
    @Size(min=3,max=50)
    private String apellido;

    @Email(message="Correo inválido")
    @NotBlank
    private String correo;

    @Pattern(
      regexp="^[0-9]{9}$",
      message="Debe contener 9 números"
    )
    private String telefono;

}