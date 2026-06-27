package com.example.ms_categoria.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_categoria.dto.CategoriaRequestDTO;
import com.example.ms_categoria.dto.CategoriaResponseDTO;
import com.example.ms_categoria.service.CategoriaService;
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
class CategoriaControllerTest {

    @Mock
    private CategoriaService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/categorias con datos validos debe retornar 201")
    void guardar_conDatosValidos_deberiaRetornar201() throws Exception {
        // Arrange: se prepara una categoría y se simula el service.
        CategoriaRequestDTO request = new CategoriaRequestDTO();
        request.setNombre("Bebidas");
        request.setDescripcion("Productos líquidos");

        CategoriaResponseDTO response = CategoriaResponseDTO.builder()
                .id(1L).nombre("Bebidas").descripcion("Productos líquidos")
                .build();
        when(service.guardar(any(CategoriaRequestDTO.class))).thenReturn(response);

        // Act + Assert: se espera creación correcta con HTTP 201.
        mockMvc.perform(post("/api/categorias")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Bebidas"));
    }

    @Test
    @DisplayName("GET /api/categorias debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula una lista de categorías.
        when(service.listar()).thenReturn(List.of(CategoriaResponseDTO.builder()
                .id(1L).nombre("Bebidas").descripcion("Productos líquidos").build()));

        // Act + Assert: se espera HTTP 200 y la categoría en la respuesta.
        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Bebidas"));
    }

    @Test
    @DisplayName("DELETE /api/categorias/{id} debe retornar 204")
    void eliminar_deberiaRetornar204() throws Exception {
        // Arrange: se simula eliminación correcta.
        doNothing().when(service).eliminar(1L);

        // Act + Assert: se espera HTTP 204 No Content.
        mockMvc.perform(delete("/api/categorias/1"))
                .andExpect(status().isNoContent());
    }
}
