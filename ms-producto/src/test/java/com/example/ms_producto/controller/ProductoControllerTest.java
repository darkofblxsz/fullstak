package com.example.ms_producto.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_producto.dto.ProductoRequestDTO;
import com.example.ms_producto.dto.ProductoResponseDTO;
import com.example.ms_producto.services.ProductoService;
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
class ProductoControllerTest {

    @Mock
    private ProductoService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductoController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/productos con datos validos debe retornar 201")
    void guardar_conDatosValidos_deberiaRetornar201() throws Exception {
        ProductoRequestDTO request = new ProductoRequestDTO();
        request.setNombre("Notebook Lenovo");
        request.setDescripcion("Notebook para oficina");
        request.setPrecio(450000.0);
        request.setStock(10);
        request.setCategoria("Tecnologia");
        request.setMarca("Lenovo");

        ProductoResponseDTO response = ProductoResponseDTO.builder()
                .id(1L)
                .nombre("Notebook Lenovo")
                .descripcion("Notebook para oficina")
                .precio(450000.0)
                .stock(10)
                .categoria("Tecnologia")
                .marca("Lenovo")
                .build();

        when(service.guardar(any(ProductoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/productos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Notebook Lenovo"))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.categoria").value("Tecnologia"))
                .andExpect(jsonPath("$.marca").value("Lenovo"));
    }

    @Test
    @DisplayName("GET /api/productos debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        when(service.listar()).thenReturn(List.of(ProductoResponseDTO.builder()
                .id(1L)
                .nombre("Notebook Lenovo")
                .descripcion("Notebook para oficina")
                .precio(450000.0)
                .stock(10)
                .categoria("Tecnologia")
                .marca("Lenovo")
                .build()));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Notebook Lenovo"));
    }

    @Test
    @DisplayName("DELETE /api/productos/{id} debe retornar 204")
    void eliminar_deberiaRetornar204() throws Exception {
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isNoContent());
    }
}