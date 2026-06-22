package com.example.ms_pago.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @NonNull
    private Long pedidoId;
    @NonNull
    private String metodoPago;
    @NonNull
    private Double monto;
    @NonNull
    private String estado;
}