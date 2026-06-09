package com.example.ms_envio.services;



import org.springframework.stereotype.Service;

import com.example.ms_envio.Feign.ClienteClient;
import com.example.ms_envio.Feign.PedidoClient;
import com.example.ms_envio.dto.EnvioRequestDTO;
import com.example.ms_envio.dto.EnvioResponseDTO;
import com.example.ms_envio.modelo.Envio;
import com.example.ms_envio.repository.EnvioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository repository;
    private final PedidoClient pedidoClient;
    private final ClienteClient clienteClient;

    @Override
    public EnvioResponseDTO crear(EnvioRequestDTO dto) {

        log.info("Creando envio pedido {}", dto.getPedidoId());

        pedidoClient.obtener(dto.getPedidoId());
        clienteClient.obtener(dto.getClienteId());

        Envio e = Envio.builder()
                .pedidoId(dto.getPedidoId())
                .clienteId(dto.getClienteId())
                .direccion(dto.getDireccion())
                .estado("PENDIENTE")
                .build();

        repository.save(e);

        return map(e);
    }

    @Override
    public List<EnvioResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<EnvioResponseDTO> listarPorCliente(Long clienteId){
        return repository.findByClienteId(clienteId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public EnvioResponseDTO actualizarEstado(Long id, String estado){

        Envio e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        e.setEstado(estado);

        repository.save(e);

        return map(e);
    }

    private EnvioResponseDTO map(Envio e){
        return EnvioResponseDTO.builder()
                .id(e.getId())
                .pedidoId(e.getPedidoId())
                .clienteId(e.getClienteId())
                .direccion(e.getDireccion())
                .estado(e.getEstado())
                .build();
    }
}