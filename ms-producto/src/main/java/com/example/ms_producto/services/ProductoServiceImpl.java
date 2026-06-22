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
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock() == null ? 0 : dto.getStock())
                .categoria(dto.getCategoria())
                .marca(dto.getMarca())
                .categoriaId(dto.getCategoriaId())
                .proveedorId(dto.getProveedorId())
                .codigoBarras(dto.getCodigoBarras())
                .build();
        repository.save(producto);
        return map(producto);
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return repository.findAll().stream().map(this::map).toList();
    }

    @Override
    public ProductoResponseDTO buscar(Long id) {
        return map(repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado")));
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto producto = repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock() == null ? producto.getStock() : dto.getStock());
        producto.setCategoria(dto.getCategoria());
        producto.setMarca(dto.getMarca());
        producto.setCategoriaId(dto.getCategoriaId());
        producto.setProveedorId(dto.getProveedorId());
        producto.setCodigoBarras(dto.getCodigoBarras());
        repository.save(producto);
        return map(producto);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repository.deleteById(id);
    }

    private ProductoResponseDTO map(Producto producto) {
        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(producto.getCategoria())
                .marca(producto.getMarca())
                .categoriaId(producto.getCategoriaId())
                .proveedorId(producto.getProveedorId())
                .codigoBarras(producto.getCodigoBarras())
                .build();
    }
}
