package com.example.ms_boleta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.ms_boleta.model.Boleta;
import com.example.ms_boleta.repository.BoletaRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaService boletaService;

    @Test
    @DisplayName("guardar debe calcular IVA y total final")
    void guardar_deberiaCalcularIvaYTotalFinal() {
        // Given: se prepara una boleta con total base de 1000.
        Boleta boleta = new Boleta();
        boleta.setTotal(1000.0);
        when(boletaRepository.save(boleta)).thenReturn(boleta);

        // When: se ejecuta la regla de negocio del service.
        Boleta resultado = boletaService.guardar(boleta);

        // Then: se valida que el IVA sea 19% y el total final sea total + IVA.
        assertEquals(190.0, resultado.getIva());
        assertEquals(1190.0, resultado.getTotalFinal());
        verify(boletaRepository).save(boleta);
    }

    @Test
    @DisplayName("listar debe devolver todas las boletas")
    void listar_deberiaRetornarBoletas() {
        // Given: el repository devuelve una lista simulada.
        Boleta boleta = new Boleta();
        boleta.setId(1L);
        when(boletaRepository.findAll()).thenReturn(List.of(boleta));

        // When: se solicita el listado.
        List<Boleta> resultado = boletaService.listar();

        // Then: se espera una boleta en la respuesta.
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
    }
}
