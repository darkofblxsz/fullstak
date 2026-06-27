package com.example.ms_envio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_envio.dto.EnvioRequestDTO;
import com.example.ms_envio.dto.EnvioResponseDTO;
import com.example.ms_envio.services.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class EnvioControllerTest {

    @Mock
    private EnvioService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new EnvioController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/envios con datos validos debe retornar 201")
    void crear_conDatosValidos_deberiaRetornar201() throws Exception {
        // Arrange: se prepara una solicitud de envío.
        EnvioRequestDTO request = new EnvioRequestDTO();
        request.setPedidoId(1L);
        request.setClienteId(5L);
        request.setDireccion("Av Siempre Viva 123");

        EnvioResponseDTO response = EnvioResponseDTO.builder()
                .id(1L).pedidoId(1L).clienteId(5L).direccion("Av Siempre Viva 123").estado("PENDIENTE")
                .build();
        when(service.crear(any(EnvioRequestDTO.class))).thenReturn(response);

        // Act + Assert: se espera HTTP 201 Created.
        mockMvc.perform(post("/api/envios")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    @DisplayName("GET /api/envios debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula un envío registrado.
        when(service.listar()).thenReturn(List.of(EnvioResponseDTO.builder()
                .id(1L).pedidoId(1L).clienteId(5L).direccion("Av Siempre Viva 123").estado("PENDIENTE").build()));

        // Act + Assert: se espera HTTP 200 OK.
        mockMvc.perform(get("/api/envios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clienteId").value(5L));
    }

    @Test
    @DisplayName("PUT /api/envios/{id}/estado debe actualizar estado")
    void actualizarEstado_deberiaRetornar200() throws Exception {
        // Arrange: se simula actualización de estado.
        when(service.actualizarEstado(1L, "ENVIADO")).thenReturn(EnvioResponseDTO.builder()
                .id(1L).pedidoId(1L).clienteId(5L).direccion("Av Siempre Viva 123").estado("ENVIADO").build());

        // Act + Assert: se espera HTTP 200 y el estado actualizado.
        mockMvc.perform(put("/api/envios/1/estado").param("estado", "ENVIADO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ENVIADO"));
    }
}
