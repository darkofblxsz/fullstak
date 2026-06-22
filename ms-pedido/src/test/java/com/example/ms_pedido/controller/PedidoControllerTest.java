package com.example.ms_pedido.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_pedido.dto.PedidoItemDTO;
import com.example.ms_pedido.dto.PedidoRequestDTO;
import com.example.ms_pedido.dto.PedidoResponseDTO;
import com.example.ms_pedido.service.PedidoService;
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
class PedidoControllerTest {

    @Mock
    private PedidoService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/pedidos con datos validos debe retornar 201")
    void crear_conDatosValidos_deberiaRetornar201() throws Exception {
        // Arrange: se prepara un pedido con un producto.
        PedidoItemDTO item = new PedidoItemDTO();
        item.setProductoId(10L);
        item.setCantidad(2);
        PedidoRequestDTO request = new PedidoRequestDTO();
        request.setClienteId(5L);
        request.setItems(List.of(item));

        PedidoResponseDTO response = PedidoResponseDTO.builder()
                .id(1L).clienteId(5L).estado("CREADO").total(2000.0).build();
        when(service.crearPedido(any(PedidoRequestDTO.class))).thenReturn(response);

        // Act + Assert: se espera HTTP 201 Created.
        mockMvc.perform(post("/api/pedidos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value("CREADO"));
    }

    @Test
    @DisplayName("GET /api/pedidos debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula un pedido creado.
        when(service.listar()).thenReturn(List.of(PedidoResponseDTO.builder()
                .id(1L).clienteId(5L).estado("CREADO").total(2000.0).build()));

        // Act + Assert: se espera HTTP 200 OK.
        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @DisplayName("GET /api/pedidos/{id} debe retornar pedido")
    void obtener_deberiaRetornar200() throws Exception {
        // Arrange: se simula búsqueda por ID.
        when(service.obtener(1L)).thenReturn(PedidoResponseDTO.builder()
                .id(1L).clienteId(5L).estado("CREADO").total(2000.0).build());

        // Act + Assert: se espera HTTP 200 y el ID solicitado.
        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
