package com.example.ms_boleta.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_boleta.model.Boleta;
import com.example.ms_boleta.service.BoletaService;
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
class BoletaControllerTest {

    @Mock
    private BoletaService boletaService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BoletaController(boletaService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /boletas debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula una boleta registrada.
        Boleta boleta = new Boleta(1L, 10L, 1L, 10000.0, "2026-06-20", "DEBITO", "PAGADA", 1900.0, 11900.0);
        when(boletaService.listar()).thenReturn(List.of(boleta));

        // Act + Assert: se espera HTTP 200 y el total final.
        mockMvc.perform(get("/boletas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].totalFinal").value(11900.0));
    }

    @Test
    @DisplayName("POST /boletas debe guardar boleta")
    void guardar_deberiaRetornar200() throws Exception {
        // Arrange: se prepara una boleta con total.
        Boleta boleta = new Boleta(null, 10L, 1L, 10000.0, "2026-06-20", "DEBITO", "PAGADA", null, null);
        Boleta respuesta = new Boleta(1L, 10L, 1L, 10000.0, "2026-06-20", "DEBITO", "PAGADA", 1900.0, 11900.0);
        when(boletaService.guardar(any(Boleta.class))).thenReturn(respuesta);

        // Act + Assert: el controller devuelve la boleta guardada.
        mockMvc.perform(post("/boletas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(boleta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.iva").value(1900.0));
    }

    @Test
    @DisplayName("DELETE /boletas/{id} debe retornar mensaje")
    void eliminar_deberiaRetornar200() throws Exception {
        // Arrange: se simula eliminación correcta.
        doNothing().when(boletaService).eliminar(1L);

        // Act + Assert: se espera HTTP 200 porque el controller retorna un String.
        mockMvc.perform(delete("/boletas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Boleta eliminada correctamente"));
    }
}
