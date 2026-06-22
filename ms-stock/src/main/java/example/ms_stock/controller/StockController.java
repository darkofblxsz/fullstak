package example.ms_stock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import example.ms_stock.model.Stock;
import example.ms_stock.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/stock")
@Tag(name = "Stock", description = "Inventario de productos del supermercado")
public class StockController {

    @Autowired
    private StockService stockService;

    @Operation(summary = "Registrar stock inicial de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stock creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida")
    })
    @PostMapping
    public ResponseEntity<Stock> guardar(@RequestBody Stock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.guardar(stock));
    }

    @Operation(summary = "Listar todo el inventario")
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(stockService.listar());
    }

    @Operation(summary = "Validar disponibilidad de stock")
    @GetMapping("/validar/{productoId}")
    public ResponseEntity<Boolean> validar(@PathVariable Long productoId) {
        return ResponseEntity.ok(stockService.tieneStock(productoId));
    }

    @Operation(summary = "Obtener detalle de stock por producto")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Stock> obtenerDetalle(@PathVariable Long productoId) {
        return ResponseEntity.ok(stockService.obtenerPorProductoId(productoId));
    }

    @Operation(summary = "Descontar stock cuando se vende un producto")
    @PutMapping("/descontar/{productoId}")
    public ResponseEntity<String> descontar(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        stockService.descontarStock(productoId, cantidad);
        return ResponseEntity.ok("Stock actualizado correctamente");
    }

    @Operation(summary = "Aumentar stock cuando entra mercaderia")
    @PutMapping("/aumentar/{productoId}")
    public ResponseEntity<Stock> aumentar(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(stockService.aumentarStock(productoId, cantidad));
    }

    @Operation(summary = "Actualizar cantidad exacta de stock")
    @PutMapping("/producto/{productoId}")
    public ResponseEntity<Stock> actualizar(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(stockService.actualizarCantidad(productoId, cantidad));
    }

    @Operation(summary = "Probar manejo de error")
    @GetMapping("/test-error/{id}")
    public ResponseEntity<?> testError(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.obtenerPorProductoId(id));
    }
}
