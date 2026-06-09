package com.example.ms_pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_pedido.dto.StockResponseDTO;

@FeignClient(name="ms-stock")
public interface StockClient {

    @GetMapping("/api/stock/producto/{id}")
    StockResponseDTO obtenerStock(@PathVariable Long id);
}