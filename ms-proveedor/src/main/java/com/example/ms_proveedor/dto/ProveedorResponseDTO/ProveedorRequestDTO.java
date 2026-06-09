package com.example.ms_proveedor.dto.ProveedorResponseDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProveedorRequestDTO {

    @NotBlank
    @Size(min=3,max=50)
    private String nombre;

    @Email
    private String correo;

    @Pattern(regexp="^[0-9]{9}$")
    private String telefono;
}