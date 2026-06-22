package example.ms_stock.service;

import java.util.List;

import example.ms_stock.exception.StockNotFoundException;
import example.ms_stock.model.Stock;
import example.ms_stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock guardar(Stock stock) {
        if (stock.getCantidad() == null) stock.setCantidad(0);
        return stockRepository.save(stock);
    }

    public List<Stock> listar() {
        return stockRepository.findAll();
    }

    public boolean tieneStock(Long productoId) {
        return stockRepository.findByProductoId(productoId)
                .map(stock -> stock.getCantidad() != null && stock.getCantidad() > 0)
                .orElse(false);
    }

    @Transactional
    public void descontarStock(Long productoId, Integer cantidadAValidar) {
        Stock stock = obtenerPorProductoId(productoId);
        if (cantidadAValidar == null || cantidadAValidar <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }
        if (stock.getCantidad() < cantidadAValidar) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + stock.getCantidad());
        }
        stock.setCantidad(stock.getCantidad() - cantidadAValidar);
        stockRepository.save(stock);
    }

    @Transactional
    public Stock aumentarStock(Long productoId, Integer cantidad) {
        Stock stock = obtenerPorProductoId(productoId);
        if (cantidad == null || cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }
        stock.setCantidad(stock.getCantidad() + cantidad);
        return stockRepository.save(stock);
    }

    @Transactional
    public Stock actualizarCantidad(Long productoId, Integer cantidad) {
        Stock stock = obtenerPorProductoId(productoId);
        if (cantidad == null || cantidad < 0) {
            throw new RuntimeException("La cantidad no puede ser negativa");
        }
        stock.setCantidad(cantidad);
        return stockRepository.save(stock);
    }

    public Stock obtenerPorProductoId(Long productoId) {
        return stockRepository.findByProductoId(productoId)
                .orElseThrow(() -> new StockNotFoundException("Producto no registrado en stock"));
    }
}
