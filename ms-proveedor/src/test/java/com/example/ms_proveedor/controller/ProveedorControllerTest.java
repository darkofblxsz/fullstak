package com.example.ms_proveedor.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorRequestDTO;
import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorResponseDTO;
import com.example.ms_proveedor.service.ProveedorService;
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
class ProveedorControllerTest {

    @Mock
    private ProveedorService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProveedorController(service)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("POST /api/proveedores con datos validos debe retornar 201")
    void guardar_conDatosValidos_deberiaRetornar201() throws Exception {
        // Arrange: se prepara un proveedor válido.
        ProveedorRequestDTO request = new ProveedorRequestDTO();
        request.setNombre("Proveedor Uno");
        request.setCorreo("proveedor@mail.com");
        request.setTelefono("123456789");

        ProveedorResponseDTO response = ProveedorResponseDTO.builder()
                .id(1L).nombre("Proveedor Uno").correo("proveedor@mail.com").telefono("123456789")
                .build();
        when(service.guardar(any(ProveedorRequestDTO.class))).thenReturn(response);

        // Act + Assert: se espera HTTP 201 Created.
        mockMvc.perform(post("/api/proveedores")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Proveedor Uno"));
    }

    @Test
    @DisplayName("GET /api/proveedores debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula un proveedor registrado.
        when(service.listar()).thenReturn(List.of(ProveedorResponseDTO.builder()
                .id(1L).nombre("Proveedor Uno").correo("proveedor@mail.com").telefono("123456789").build()));

        // Act + Assert: se espera HTTP 200 OK.
        mockMvc.perform(get("/api/proveedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Proveedor Uno"));
    }

    @Test
    @DisplayName("DELETE /api/proveedores/{id} debe retornar 204")
    void eliminar_deberiaRetornar204() throws Exception {
        // Arrange: se simula eliminación correcta.
        doNothing().when(service).eliminar(1L);

        // Act + Assert: se espera HTTP 204 No Content.
        mockMvc.perform(delete("/api/proveedores/1"))
                .andExpect(status().isNoContent());
    }
}
