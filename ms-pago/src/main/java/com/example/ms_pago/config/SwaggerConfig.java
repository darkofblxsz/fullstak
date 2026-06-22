package com.example.ms_pago.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8082").description("Servidor local"))
                .info(new Info()
                        .title("API Pagos - Supermercado")
                        .version("1.0")
                        .description("Documentacion de endpoints funcionales para el sistema de supermercado."));
    }
}
