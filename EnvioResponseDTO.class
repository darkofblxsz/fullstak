package com.example.ms_envio.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "envios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Long clienteId;

    private String direccion;

    private String estado;
}