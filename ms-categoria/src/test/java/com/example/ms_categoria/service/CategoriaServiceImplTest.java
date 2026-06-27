package com.example.ms_categoria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.ms_categoria.dto.CategoriaRequestDTO;
import com.example.ms_categoria.dto.CategoriaResponseDTO;
import com.example.ms_categoria.exception.ResourceNotFoundException;
import com.example.ms_categoria.model.Categoria;
import com.example.ms_categoria.repository.CategoriaRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private CategoriaServiceImpl service;

    @Test
    @DisplayName("guardar debe crear categoria cuando el nombre no existe")
    void guardar_conNombreNuevo_deberiaCrearCategoria() {
        // Given: nombre disponible.
        CategoriaRequestDTO dto = new CategoriaRequestDTO();
        dto.setNombre("Bebidas");
        dto.setDescripcion("Productos líquidos");
        when(repository.findByNombre("Bebidas")).thenReturn(Optional.empty());
        when(repository.save(any(Categoria.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: se guarda.
        CategoriaResponseDTO resultado = service.guardar(dto);

        // Then: se valida respuesta y persistencia.
        assertEquals("Bebidas", resultado.getNombre());
        verify(repository).save(any(Categoria.class));
    }

    @Test
    @DisplayName("guardar debe fallar cuando la categoria ya existe")
    void guardar_conNombreDuplicado_deberiaLanzarError() {
        // Given: nombre duplicado.
        CategoriaRequestDTO dto = new CategoriaRequestDTO();
        dto.setNombre("Bebidas");
        when(repository.findByNombre("Bebidas")).thenReturn(Optional.of(Categoria.builder().nombre("Bebidas").build()));

        // When + Then: se espera error.
        assertThrows(RuntimeException.class, () -> service.guardar(dto));
    }

    @Test
    @DisplayName("buscarPorId debe fallar cuando no existe")
    void buscarPorId_sinCategoria_deberiaLanzarResourceNotFound() {
        // Given: id inexistente.
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When + Then: se espera excepción.
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    @DisplayName("listar debe mapear categorias")
    void listar_deberiaRetornarListaDto() {
        // Given: categorias simuladas.
        when(repository.findAll()).thenReturn(List.of(Categoria.builder().id(1L).nombre("Abarrotes").descripcion("Base").build()));

        // When: se lista.
        List<CategoriaResponseDTO> resultado = service.listar();

        // Then: se valida mapeo.
        assertEquals(1, resultado.size());
        assertEquals("Abarrotes", resultado.get(0).getNombre());
    }
}
