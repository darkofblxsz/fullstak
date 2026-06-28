package example.ms_stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import example.ms_stock.exception.StockNotFoundException;
import example.ms_stock.model.Stock;
import example.ms_stock.repository.StockRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockService = new StockService();
        ReflectionTestUtils.setField(stockService, "stockRepository", stockRepository);
    }

    @Test
    @DisplayName("tieneStock debe retornar true cuando cantidad es mayor a cero")
    void tieneStock_conCantidadMayorACero_deberiaRetornarTrue() {
        // Arrange: se simula stock disponible.
        when(stockRepository.findByProductoId(1L)).thenReturn(Optional.of(new Stock(1L, 1L, 5)));

        // Act: se consulta disponibilidad.
        boolean resultado = stockService.tieneStock(1L);

        // Assert: se espera true.
        assertTrue(resultado);
    }

    @Test
    @DisplayName("tieneStock debe retornar false cuando no existe registro")
    void tieneStock_sinRegistro_deberiaRetornarFalse() {
        // Arrange: se simula que no existe stock.
        when(stockRepository.findByProductoId(99L)).thenReturn(Optional.empty());

        // Act: se consulta disponibilidad.
        boolean resultado = stockService.tieneStock(99L);

        // Assert: se espera false.
        assertFalse(resultado);
    }

    @Test
    @DisplayName("descontarStock debe restar cantidad y guardar")
    void descontarStock_conCantidadSuficiente_deberiaGuardarNuevoStock() {
        // Arrange: stock inicial 10 y se descuentan 3.
        Stock stock = new Stock(1L, 1L, 10);
        when(stockRepository.findByProductoId(1L)).thenReturn(Optional.of(stock));

        // Act: se descuenta stock.
        stockService.descontarStock(1L, 3);

        // Assert: se espera que queden 7 unidades y que se guarde.
        assertEquals(7, stock.getCantidad());
        verify(stockRepository).save(stock);
    }

    @Test
    @DisplayName("descontarStock debe fallar cuando no hay stock suficiente")
    void descontarStock_conCantidadInsuficiente_deberiaLanzarError() {
        // Arrange: stock inicial menor a la cantidad solicitada.
        when(stockRepository.findByProductoId(1L)).thenReturn(Optional.of(new Stock(1L, 1L, 2)));

        // Act + Assert: se espera RuntimeException.
        assertThrows(RuntimeException.class, () -> stockService.descontarStock(1L, 5));
    }

    @Test
    @DisplayName("obtenerPorProductoId debe fallar cuando no existe")
    void obtenerPorProductoId_sinRegistro_deberiaLanzarStockNotFound() {
        // Arrange: no existe registro del producto.
        when(stockRepository.findByProductoId(99L)).thenReturn(Optional.empty());

        // Act + Assert: se espera excepción personalizada.
        assertThrows(StockNotFoundException.class, () -> stockService.obtenerPorProductoId(99L));
    }
}
