package com.example.ms_carrito.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ms_carrito.dto.CarritoItemRequestDTO;
import com.example.ms_carrito.dto.CarritoItemResponseDTO;
import com.example.ms_carrito.dto.CarritoResponseDTO;
import com.example.ms_carrito.modelo.Carrito;
import com.example.ms_carrito.modelo.CarritoItem;
import com.example.ms_carrito.repository.CarritoItemRepository;
import com.example.ms_carrito.repository.CarritoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository itemRepository;

    @Override
    public CarritoResponseDTO obtenerCarrito(Long clienteId) {
        Carrito carrito = carritoRepository.findByClienteId(clienteId)
                .orElseGet(() -> carritoRepository.save(Carrito.builder().clienteId(clienteId).build()));
        return map(carrito);
    }

    @Override
    @Transactional
    public CarritoResponseDTO agregarProducto(Long clienteId, CarritoItemRequestDTO dto) {
        Carrito carrito = carritoRepository.findByClienteId(clienteId)
                .orElseGet(() -> carritoRepository.save(Carrito.builder().clienteId(clienteId).build()));

        CarritoItem item = itemRepository.findByCarritoIdAndProductoId(carrito.getId(), dto.getProductoId())
                .orElse(CarritoItem.builder()
                        .carritoId(carrito.getId())
                        .productoId(dto.getProductoId())
                        .cantidad(0)
                        .build());
        item.setCantidad(item.getCantidad() + dto.getCantidad());
        itemRepository.save(item);
        return map(carrito);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long clienteId, Long productoId) {
        Carrito carrito = carritoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        itemRepository.findByCarritoIdAndProductoId(carrito.getId(), productoId)
                .ifPresent(itemRepository::delete);
    }

    @Override
    @Transactional
    public void limpiarCarrito(Long clienteId) {
        Carrito carrito = carritoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        itemRepository.deleteByCarritoId(carrito.getId());
    }

    private CarritoResponseDTO map(Carrito carrito) {
        List<CarritoItemResponseDTO> items = itemRepository.findByCarritoId(carrito.getId())
                .stream()
                .map(i -> CarritoItemResponseDTO.builder()
                        .productoId(i.getProductoId())
                        .cantidad(i.getCantidad())
                        .build())
                .toList();
        return CarritoResponseDTO.builder()
                .carritoId(carrito.getId())
                .clienteId(carrito.getClienteId())
                .items(items)
                .build();
    }
}
