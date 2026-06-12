package com.example.ms_pedido.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pedido_detalle")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Long productoId;

    private Integer cantidad;

    private Double precio;
}