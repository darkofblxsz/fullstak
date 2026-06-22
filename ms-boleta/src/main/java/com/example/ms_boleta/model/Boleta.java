package com.example.ms_boleta.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "boletas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Long clienteId;

    private Double total;

    private String fecha;

    private String metodoPago;

    private String estado;

    private Double iva;

    private Double totalFinal;
}