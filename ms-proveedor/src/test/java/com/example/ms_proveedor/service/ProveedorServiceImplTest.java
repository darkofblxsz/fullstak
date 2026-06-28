package com.example.ms_proveedor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorRequestDTO;
import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorResponseDTO;
import com.example.ms_proveedor.exception.ResourceNotFoundException;
import com.example.ms_proveedor.model.Proveedor;
import com.example.ms_proveedor.repository.ProveedorRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceImplTest {

    @Mock
    private ProveedorRepository repository;

    @InjectMocks
    private ProveedorServiceImpl service;

    @Test
    @DisplayName("guardar debe crear proveedor cuando el nombre no existe")
    void guardar_conNombreNuevo_deberiaCrearProveedor() {
        // Given: nombre disponible.
        ProveedorRequestDTO dto = new ProveedorRequestDTO();
        dto.setNombre("Proveedor Uno");
        dto.setCorreo("proveedor@mail.com");
        dto.setTelefono("123456789");
        when(repository.findByNombre("Proveedor Uno")).thenReturn(Optional.empty());
        when(repository.save(any(Proveedor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: se guarda.
        ProveedorResponseDTO resultado = service.guardar(dto);

        // Then: se valida salida y persistencia.
        assertEquals("Proveedor Uno", resultado.getNombre());
        verify(repository).save(any(Proveedor.class));
    }

    @Test
    @DisplayName("guardar debe fallar si proveedor existe")
    void guardar_conProveedorDuplicado_deberiaLanzarError() {
        // Given: proveedor existente.
        ProveedorRequestDTO dto = new ProveedorRequestDTO();
        dto.setNombre("Proveedor Uno");
        when(repository.findByNombre("Proveedor Uno")).thenReturn(Optional.of(Proveedor.builder().nombre("Proveedor Uno").build()));

        // When + Then: se espera error de negocio.
        assertThrows(RuntimeException.class, () -> service.guardar(dto));
    }

    @Test
    @DisplayName("buscar debe fallar cuando no existe proveedor")
    void buscar_sinProveedor_deberiaLanzarResourceNotFound() {
        // Given: id inexistente.
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When + Then: se espera excepción controlada.
        assertThrows(ResourceNotFoundException.class, () -> service.buscar(99L));
    }

    @Test
    @DisplayName("listar debe mapear proveedores")
    void listar_deberiaRetornarProveedores() {
        // Given: proveedor simulado.
        when(repository.findAll()).thenReturn(List.of(Proveedor.builder().id(1L).nombre("Proveedor Dos").correo("dos@mail.com").telefono("987654321").build()));

        // When: se lista.
        List<ProveedorResponseDTO> resultado = service.listar();

        // Then: se valida mapeo.
        assertEquals(1, resultado.size());
        assertEquals("Proveedor Dos", resultado.get(0).getNombre());
    }
}
