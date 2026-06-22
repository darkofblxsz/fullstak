package com.example.ms_pago.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.ms_pago.model.Pago;
import com.example.ms_pago.service.PagoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PagoControllerTest {

    @Mock
    private PagoService service;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        PagoController controller = new PagoController();
        ReflectionTestUtils.setField(controller, "service", service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /pagos debe retornar listado")
    void listar_deberiaRetornar200() throws Exception {
        // Arrange: se simula un pago registrado.
        Pago pago = new Pago(1L, 10L, "DEBITO", 11900.0, "PAGADO");
        when(service.obtenerTodos()).thenReturn(List.of(pago));

        // Act + Assert: se espera HTTP 200 y el estado del pago.
        mockMvc.perform(get("/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PAGADO"));
    }

    @Test
    @DisplayName("POST /pagos debe guardar pago")
    void guardar_deberiaRetornar200() throws Exception {
        // Arrange: se prepara un pago válido.
        Pago pago = new Pago(1L, 10L, "DEBITO", 11900.0, "PAGADO");
        Pago respuesta = new Pago(1L, 10L, "DEBITO", 11900.0, "PAGADO");
        when(service.guardar(any(Pago.class))).thenReturn(respuesta);

        // Act + Assert: se espera HTTP 200 porque el controller devuelve directamente el objeto.
        mockMvc.perform(post("/pagos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(pago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("DELETE /pagos/{id} debe retornar 200")
    void eliminar_deberiaRetornar200() throws Exception {
        // Arrange: se simula eliminación correcta.
        doNothing().when(service).eliminar(1L);

        // Act + Assert: al ser void, Spring responde HTTP 200 sin contenido.
        mockMvc.perform(delete("/pagos/1"))
                .andExpect(status().isOk());
    }
}
