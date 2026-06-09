package com.example.ms_proveedor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorRequestDTO;
import com.example.ms_proveedor.dto.ProveedorResponseDTO.ProveedorResponseDTO;
import com.example.ms_proveedor.exception.ResourceNotFoundException;
import com.example.ms_proveedor.model.Proveedor;
import com.example.ms_proveedor.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository repository;

    @Override
    public ProveedorResponseDTO guardar(ProveedorRequestDTO dto){

        log.info("Creando proveedor {}", dto.getNombre());

        if(repository.findByNombre(dto.getNombre()).isPresent()){
        throw new RuntimeException("Proveedor existe");
        }

        Proveedor p=Proveedor.builder()
        .nombre(dto.getNombre())
        .correo(dto.getCorreo())
        .telefono(dto.getTelefono())
        .build();

        repository.save(p);

        return map(p);
    }

    @Override
    public List<ProveedorResponseDTO> listar(){
        log.info("Listando proveedores");

        return repository.findAll()
        .stream().map(this::map).toList();
    }

    @Override
    public ProveedorResponseDTO buscar(Long id){

        Proveedor p=repository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("No encontrado"));

        return map(p);
    }

    @Override
    public ProveedorResponseDTO actualizar(Long id, ProveedorRequestDTO dto){

        Proveedor p=repository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("No encontrado"));

        p.setNombre(dto.getNombre());
        p.setCorreo(dto.getCorreo());
        p.setTelefono(dto.getTelefono());

        repository.save(p);

        return map(p);
    }

    @Override
    public void eliminar(Long id){

        Proveedor p=repository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("No encontrado"));

        repository.delete(p);
    }

    private ProveedorResponseDTO map(Proveedor p){
        return ProveedorResponseDTO.builder()
        .id(p.getId())
        .nombre(p.getNombre())
        .correo(p.getCorreo())
        .telefono(p.getTelefono())
        .build();
    }
}