package com.example.ms_pedido.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ms_pedido.client.StockClient;
import com.example.ms_pedido.dto.PedidoDetalleDTO;
import com.example.ms_pedido.dto.PedidoItemDTO;
import com.example.ms_pedido.dto.PedidoRequestDTO;
import com.example.ms_pedido.dto.PedidoResponseDTO;
import com.example.ms_pedido.dto.StockResponseDTO;
import com.example.ms_pedido.exception.ResourceNotFoundException;
import com.example.ms_pedido.model.Pedido;
import com.example.ms_pedido.model.PedidoDetalle;
import com.example.ms_pedido.repository.PedidoDetalleRepository;
import com.example.ms_pedido.repository.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepo;
    private final PedidoDetalleRepository detalleRepo;
    private final StockClient stockClient;

    @Override
    public PedidoResponseDTO crearPedido(PedidoRequestDTO dto){
        double total = 0;
        Pedido pedido = Pedido.builder()
                .clienteId(dto.getClienteId())
                .total(0.0)
                .estado("CREADO")
                .build();
        pedidoRepo.save(pedido);

        List<PedidoDetalle> detalles = new ArrayList<>();
        for(PedidoItemDTO item : dto.getItems()){
            StockResponseDTO stock = stockClient.obtenerStock(item.getProductoId());
            Integer disponible = stock.getCantidadDisponible();
            if(disponible == null || disponible < item.getCantidad()){
                throw new RuntimeException("Stock insuficiente para producto " + item.getProductoId());
            }

            // Precio simulado por unidad. En una version avanzada se consulta ms-producto.
            double precio = 1000.0;
            PedidoDetalle d = PedidoDetalle.builder()
                    .pedidoId(pedido.getId())
                    .productoId(item.getProductoId())
                    .cantidad(item.getCantidad())
                    .precio(precio)
                    .build();
            detalleRepo.save(d);
            stockClient.descontar(item.getProductoId(), item.getCantidad());
            total += precio * item.getCantidad();
            detalles.add(d);
        }
        pedido.setTotal(total);
        pedido.setEstado("CONFIRMADO");
        pedidoRepo.save(pedido);
        return map(pedido, detalles);
    }

    @Override
    public PedidoResponseDTO obtener(Long id){
        Pedido p = pedidoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Pedido no encontrado"));
        return map(p, detalleRepo.findByPedidoId(id));
    }

    @Override
    public List<PedidoResponseDTO> listar(){
        return pedidoRepo.findAll().stream().map(p -> map(p, detalleRepo.findByPedidoId(p.getId()))).toList();
    }

    private PedidoResponseDTO map(Pedido p, List<PedidoDetalle> detalles){
        return PedidoResponseDTO.builder()
                .id(p.getId())
                .clienteId(p.getClienteId())
                .total(p.getTotal())
                .estado(p.getEstado())
                .items(detalles.stream()
                        .map(d->PedidoDetalleDTO.builder()
                                .productoId(d.getProductoId())
                                .cantidad(d.getCantidad())
                                .precio(d.getPrecio())
                                .build())
                        .toList())
                .build();
    }
}
