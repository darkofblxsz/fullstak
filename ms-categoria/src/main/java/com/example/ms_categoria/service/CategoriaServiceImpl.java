package com.example.ms_categoria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ms_categoria.dto.CategoriaRequestDTO;
import com.example.ms_categoria.dto.CategoriaResponseDTO;
import com.example.ms_categoria.exception.ResourceNotFoundException;
import com.example.ms_categoria.model.Categoria;
import com.example.ms_categoria.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository repository;

    @Override
    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto){
            log.info("Creando categoria {}", dto.getNombre());
        if(repository.findByNombre(dto.getNombre()).isPresent()){

            throw new RuntimeException(
            "Categoria ya existe");
        }

        Categoria categoria=
        Categoria.builder()
        .nombre(dto.getNombre())
        .descripcion(dto.getDescripcion())
        .build();

        repository.save(categoria);

        return convertirDTO(categoria);

    }

    @Override
    public List<CategoriaResponseDTO> listar(){

        log.info("Listando categorias");

        return repository.findAll()
        .stream()
        .map(this::convertirDTO)
        .toList();

    }

    @Override
    public CategoriaResponseDTO buscarPorId(Long id){

        log.info("Buscando categoria {}", id);

        Categoria categoria=
        repository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException(
            "Categoria no encontrada"));

        return convertirDTO(categoria);

    }

    @Override
    public CategoriaResponseDTO actualizar(Long id,CategoriaRequestDTO dto){
        log.info("Actualizando categoria {}", id);

        Categoria categoria=
        repository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("Categoria no encontrada"));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        repository.save(categoria);

        return convertirDTO(categoria);

    }

    @Override
    public void eliminar(Long id){

        log.info("Eliminando categoria {}", id);

        Categoria categoria=
        repository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException(
        "Categoria no encontrada"));

        repository.delete(categoria);

    }

    private CategoriaResponseDTO convertirDTO(
        Categoria categoria){

        return CategoriaResponseDTO.builder()
        .id(categoria.getId())
        .nombre(categoria.getNombre())
        .descripcion(categoria.getDescripcion())
        .build();

    }

}