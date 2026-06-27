package com.example.ms_carrito.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_carrito.dto.CarritoItemRequestDTO;
import com.example.ms_carrito.dto.CarritoItemResponseDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;
import com.example.ms_carrito.service.CarritoService;
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
class CarritoControllerTest {

    @Mock
    private CarritoService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CarritoController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /api/carrito/{clienteId} debe retornar carrito")
    void obtener_deberiaRetornar200() throws Exception {
        // Arrange: se simula un carrito con un producto.
        CarritoResponseDTO response = CarritoResponseDTO.builder()
                .carritoId(1L).clienteId(5L)
                .items(List.of(CarritoItemResponseDTO.builder().productoId(10L).cantidad(2).build()))
                .build();
        when(service.obtenerCarrito(5L)).thenReturn(response);

        // Act + Assert: se espera HTTP 200 y el clienteId.
        mockMvc.perform(get("/api/carrito/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").value(5L));
    }

    @Test
    @DisplayName("POST /api/carrito/{clienteId} debe agregar producto")
    void agregar_deberiaRetornar200() throws Exception {
        // Arrange: se prepara el producto que se agrega al carrito.
        CarritoItemRequestDTO request = new CarritoItemRequestDTO();
        request.setProductoId(10L);
        request.setCantidad(2);
        CarritoResponseDTO response = CarritoResponseDTO.builder()
                .carritoId(1L).clienteId(5L)
                .items(List.of(CarritoItemResponseDTO.builder().productoId(10L).cantidad(2).build()))
                .build();
        when(service.agregarProducto(any(Long.class), any(CarritoItemRequestDTO.class))).thenReturn(response);

        // Act + Assert: se espera HTTP 200 y el producto agregado.
        mockMvc.perform(post("/api/carrito/5")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].productoId").value(10L));
    }

    @Test
    @DisplayName("DELETE /api/carrito/{clienteId}/producto/{productoId} debe retornar 204")
    void eliminarProducto_deberiaRetornar204() throws Exception {
        // Arrange: se simula eliminación del producto del carrito.
        doNothing().when(service).eliminarProducto(5L, 10L);

        // Act + Assert: se espera HTTP 204 No Content.
        mockMvc.perform(delete("/api/carrito/5/producto/10"))
                .andExpect(status().isNoContent());
    }
}
