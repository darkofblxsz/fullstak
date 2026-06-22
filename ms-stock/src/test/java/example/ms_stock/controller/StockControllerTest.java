package example.ms_stock.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import example.ms_stock.model.Stock;
import example.ms_stock.service.StockService;

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
class StockControllerTest {

    @Mock
    private StockService stockService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        StockController controller = new StockController();
        ReflectionTestUtils.setField(controller, "stockService", stockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("GET /api/stock/validar/{productoId} debe retornar true")
    void validar_conStockDisponible_deberiaRetornarTrue() throws Exception {
        // Arrange: se simula que el producto tiene stock.
        when(stockService.tieneStock(1L)).thenReturn(true);

        // Act + Assert: se espera HTTP 200 y respuesta true.
        mockMvc.perform(get("/api/stock/validar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("GET /api/stock/producto/{productoId} debe retornar detalle")
    void obtenerDetalle_deberiaRetornar200() throws Exception {
        // Arrange: se prepara un detalle de stock simulado.
        when(stockService.obtenerPorProductoId(1L)).thenReturn(new Stock(1L, 1L, 10));

        // Act + Assert: se espera HTTP 200 y la cantidad del producto.
        mockMvc.perform(get("/api/stock/producto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(10));
    }

    @Test
    @DisplayName("PUT /api/stock/descontar/{productoId} debe retornar mensaje correcto")
    void descontar_deberiaRetornar200() throws Exception {
        // Arrange: se simula descuento correcto.
        doNothing().when(stockService).descontarStock(1L, 2);

        // Act + Assert: se espera HTTP 200 y mensaje de confirmación.
        mockMvc.perform(put("/api/stock/descontar/1").param("cantidad", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock actualizado correctamente"));
    }
}
