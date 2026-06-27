package com.example.ms_cliente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.ms_cliente.dto.ClienteRequestDTO;
import com.example.ms_cliente.dto.ClienteResposeDTO;
import com.example.ms_cliente.exception.ResourceNotFoundException;
import com.example.ms_cliente.model.Cliente;
import com.example.ms_cliente.repository.ClienteRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteServiceImpl service;

    @Test
    @DisplayName("guardar debe crear cliente cuando el correo no existe")
    void guardar_conCorreoNuevo_deberiaCrearCliente() {
        // Given: correo libre y datos válidos.
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setNombre("Juan");
        dto.setApellido("Perez");
        dto.setCorreo("juan@mail.com");
        dto.setTelefono("123456789");
        when(repository.findByCorreo("juan@mail.com")).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When: se guarda el cliente.
        ClienteResposeDTO resultado = service.guardar(dto);

        // Then: se valida el DTO de salida y que se haya guardado.
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@mail.com", resultado.getCorreo());
        verify(repository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("guardar debe fallar cuando el correo ya existe")
    void guardar_conCorreoDuplicado_deberiaLanzarError() {
        // Given: el correo ya existe en la base simulada.
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setCorreo("repetido@mail.com");
        when(repository.findByCorreo("repetido@mail.com"))
                .thenReturn(Optional.of(Cliente.builder().correo("repetido@mail.com").build()));

        // When + Then: se espera una excepción por duplicidad.
        assertThrows(RuntimeException.class, () -> service.guardar(dto));
    }

    @Test
    @DisplayName("buscarPorId debe fallar cuando no encuentra el cliente")
    void buscarPorId_sinCliente_deberiaLanzarResourceNotFound() {
        // Given: no existe cliente con ese id.
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When + Then: se espera excepción controlada.
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    @DisplayName("listar debe mapear clientes a DTO")
    void listar_deberiaRetornarListaDto() {
        // Given: un cliente guardado en el repository simulado.
        Cliente cliente = Cliente.builder().id(1L).nombre("Ana").apellido("Diaz").correo("ana@mail.com").telefono("987654321").build();
        when(repository.findAll()).thenReturn(List.of(cliente));

        // When: se lista.
        List<ClienteResposeDTO> resultado = service.listar();

        // Then: se verifica el mapeo.
        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombre());
    }
}
