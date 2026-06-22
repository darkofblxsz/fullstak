package com.example.ms_cliente.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_cliente.dto.ClienteRequestDTO;
import com.example.ms_cliente.dto.ClienteResposeDTO;
import com.example.ms_cliente.service.ClienteService;
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
class ClienteControllerTest {

    @Mock
    private ClienteService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/clientes con datos validos debe retornar 201")
    void guardar_conDatosValidos_deberiaRetornar201() throws Exception {
        // Arrange: se prepara un cliente válido y se simula la respuesta del service.
        ClienteRequestDTO request = new ClienteRequestDTO();
        request.setNombre("Juan");
        request.setApellido("Perez");
        request.setCorreo("juan@mail.com");
        request.setTelefono("123456789");

        ClienteResposeDTO response = ClienteResposeDTO.builder()
                .id(1L).nombre("Juan").apellido("Perez").correo("juan@mail.com").telefono("123456789")
                .build();
        when(service.guardar(any(ClienteRequestDTO.class))).thenReturn(response);

        // Act + Assert: se ejecuta el POST y se espera HTTP 201 Created.
        mockMvc.perform(post("/api/clientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.correo").value("juan@mail.com"));
    }

    @Test
    @DisplayName("GET /api/clientes debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula una lista con un cliente.
        ClienteResposeDTO response = ClienteResposeDTO.builder()
                .id(1L).nombre("Juan").apellido("Perez").correo("juan@mail.com").telefono("123456789")
                .build();
        when(service.listar()).thenReturn(List.of(response));

        // Act + Assert: se consulta el endpoint y se espera HTTP 200 OK.
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    @DisplayName("DELETE /api/clientes/{id} debe retornar 204")
    void eliminar_deberiaRetornar204() throws Exception {
        // Arrange: se simula que el service elimina sin errores.
        doNothing().when(service).eliminar(1L);

        // Act + Assert: se elimina el cliente y se espera HTTP 204 No Content.
        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
