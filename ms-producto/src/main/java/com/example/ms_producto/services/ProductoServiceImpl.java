package com.example.ms_producto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ms_producto.dto.ProductoRequestDTO;
import com.example.ms_producto.dto.ProductoResponseDTO;
import com.example.ms_producto.producto.Producto;
import com.example.ms_producto.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    @Override
    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {

        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .precio(dto.getPrecio())
                .build();

        repository.save(producto);

        return map(producto);
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public ProductoResponseDTO buscar(Long id) {

        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return map(producto);
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {

        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());

        repository.save(producto);

        return map(producto);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private ProductoResponseDTO map(Producto producto) {
        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .build();
    }
}