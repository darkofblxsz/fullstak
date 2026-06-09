package example.ms_stock.controller;

import example.ms_stock.model.Stock;
import example.ms_stock.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * Endpoint para consultar disponibilidad.
     * Acceso: GET http://localhost:8080/api/stock/validar/{id} (vía Gateway)
     */
    @GetMapping("/validar/{productoId}")
    public ResponseEntity<Boolean> validar(@PathVariable Long productoId) {
        // Retornamos 200 OK con el valor booleano
        return ResponseEntity.ok(stockService.tieneStock(productoId));
    }

    /**
     * Endpoint para obtener el detalle de stock de un producto.
     */
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Stock> obtenerDetalle(@PathVariable Long productoId) {
        // Si el Service lanza StockNotFoundException, el GlobalExceptionHandler lo atrapa
        return ResponseEntity.ok(stockService.obtenerPorProductoId(productoId));
    }

    /**
     * Endpoint para actualizar/descontar stock.
     * Se usa cuando se concreta un pedido.
     */
    @PutMapping("/descontar/{productoId}")
    public ResponseEntity<String> descontar(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        stockService.descontarStock(productoId, cantidad);
        return ResponseEntity.ok("Stock actualizado correctamente");
    }

    /**
     * Endpoint opcional para manejo de errores de prueba.
     * Si pides un ID que no existe, verás el JSON del Exception Handler.
     */
    @GetMapping("/test-error/{id}")
    public ResponseEntity<?> testError(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.obtenerPorProductoId(id));
    }
}
