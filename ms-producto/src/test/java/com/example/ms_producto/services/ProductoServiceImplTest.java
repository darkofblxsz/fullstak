package com.example.ms_producto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.ms_producto.dto.ProductoRequestDTO;
import com.example.ms_producto.dto.ProductoResponseDTO;
import com.example.ms_producto.producto.Producto;
import com.example.ms_producto.repository.ProductoRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoServiceImpl service;

    @Test
    @DisplayName("guardar debe crear producto y retornar DTO")
    void guardar_deberiaCrearProducto() {
        // Given: datos válidos de producto.
        ProductoRequestDTO dto = new ProductoRequestDTO();
        dto.setNombre("Arroz");
        dto.setPrecio(1200.0);
        when(repository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: se guarda el producto.
        ProductoResponseDTO resultado = service.guardar(dto);

        // Then: se valida el mapeo principal.
        assertEquals("Arroz", resultado.getNombre());
        assertEquals(1200.0, resultado.getPrecio());
        verify(repository).save(any(Producto.class));
    }

    @Test
    @DisplayName("buscar debe fallar cuando no existe producto")
    void buscar_sinProducto_deberiaLanzarError() {
        // Given: producto no encontrado.
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When + Then: se espera error.
        assertThrows(RuntimeException.class, () -> service.buscar(99L));
    }

    @Test
    @DisplayName("listar debe retornar productos mapeados")
    void listar_deberiaRetornarProductos() {
        // Given: un producto simulado.
        Producto producto = Producto.builder().id(1L).nombre("Leche").precio(1000.0).build();
        when(repository.findAll()).thenReturn(List.of(producto));

        // When: se lista.
        List<ProductoResponseDTO> resultado = service.listar();

        // Then: se valida salida.
        assertEquals(1, resultado.size());
        assertEquals("Leche", resultado.get(0).getNombre());
    }
}
