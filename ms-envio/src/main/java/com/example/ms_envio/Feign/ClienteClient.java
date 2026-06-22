package com.example.ms_envio.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    Object obtener(@PathVariable Long id);
}