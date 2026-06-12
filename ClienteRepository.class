package com.example.ms_cliente.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ms_cliente.dto.ClienteRequestDTO;
import com.example.ms_cliente.dto.ClienteResposeDTO;
import com.example.ms_cliente.exception.ResourceNotFoundException;
import com.example.ms_cliente.model.Cliente;
import com.example.ms_cliente.repository.ClienteRepository;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteServiceImpl
implements ClienteService{

    private final ClienteRepository repository = null;

    @Override
    public ClienteResposeDTO guardar(ClienteRequestDTO dto){

        log.info(
        "Creando cliente {}",
        dto.getCorreo());

        if(repository.findByCorreo(
        dto.getCorreo()).isPresent()){

        throw new RuntimeException(
        "Correo ya existe");
        }

        Cliente cliente=
        Cliente.builder()
        .nombre(dto.getNombre())
        .apellido(dto.getApellido())
        .correo(dto.getCorreo())
        .telefono(dto.getTelefono())
        .build();

        repository.save(cliente);

        log.info(
        "Cliente creado {}",
        cliente.getId());

        return convertirDTO(cliente);

    }

    @Override
    public List<ClienteResposeDTO>
    listar(){

        log.info(
        "Listando clientes");

        return repository.findAll()
        .stream()
        .map(this::convertirDTO)
        .toList();

    }

    @Override
    public ClienteResposeDTO
        buscarPorId(Long id){

        log.info(
        "Buscando cliente {}",
        id);

        Cliente cliente=
        repository.findById(id).orElseThrow(()->new ResourceNotFoundException(
        "Cliente no encontrado"));

        return convertirDTO(cliente);

    }

    @Override
    public ClienteResposeDTO actualizar(
        Long id,
        ClienteRequestDTO dto){

        log.info(
        "Actualizando cliente {}",
        id);

        Cliente cliente=
        repository.findById(id).orElseThrow(()->new ResourceNotFoundException(
        "Cliente no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setCorreo(dto.getCorreo());
        cliente.setTelefono(dto.getTelefono());

        repository.save(cliente);

        return convertirDTO(cliente);

    }

    @Override
    public void eliminar(Long id){

        log.info(
        "Eliminando cliente {}",
        id);

        Cliente cliente=
        repository.findById(id).orElseThrow(()->new ResourceNotFoundException(
            "Cliente no encontrado"));

        repository.delete(cliente);

    }

    private ClienteResposeDTO convertirDTO(Cliente cliente){

        return ClienteResposeDTO.builder()
        .id(cliente.getId())
        .nombre(cliente.getNombre())
        .apellido(cliente.getApellido())
        .correo(cliente.getCorreo())
        .telefono(cliente.getTelefono())
        .build();

    }

}