# Comparación entre RAR entregado y ZIP revisado anterior

Se comparó el listado de archivos del RAR subido por el usuario contra el ZIP revisado anterior.

## Diferencias relevantes

- El RAR original trae muchos archivos dentro de carpetas `target/`. Para la entrega en GitHub normalmente conviene no subir `target/`, porque son archivos generados al compilar.
- El RAR original conserva tests automáticos `*ApplicationTests.java` en algunos módulos. En la guía de pruebas se recomienda eliminarlos para no levantar toda la aplicación innecesariamente.
- El ZIP revisado anterior ya contenía tests unitarios nuevos en `src/test/java` para varios microservicios.

## Conteo técnico

- Archivos detectados en RAR normalizado: 888
- Archivos detectados en ZIP revisado anterior: 399
- Archivos solo en RAR: 509
- Archivos solo en ZIP revisado anterior: 20
- Archivos `target/` detectados solo en RAR: 491
- Tests automáticos detectados solo en RAR: 12

## Tests automáticos detectados en el RAR

- api-gateway/src/test/java/example/api_gateway/ApiGatewayApplicationTests.java
- eureka-server/src/test/java/example/eureka_server/EurekaServerApplicationTests.java
- ms-boleta/src/test/java/com/example/ms_boleta/MsBoletaApplicationTests.java
- ms-carrito/src/test/java/com/example/ms_carrito/MsCarritoApplicationTests.java
- ms-categoria/src/test/java/com/example/ms_categoria/MsCategoriaApplicationTests.java
- ms-cliente/src/test/java/com/example/ms_cliente/MsClienteApplicationTests.java
- ms-envio/src/test/java/com/example/ms_envio/MsEnvioApplicationTests.java
- ms-pago/src/test/java/com/example/ms_pago/MsPagoApplicationTests.java
- ms-pedido/src/test/java/com/example/ms_pedido/MsPedidoApplicationTests.java
- ms-producto/src/test/java/com/example/ms_producto/MsProductoApplicationTests.java
- ms-proveedor/src/test/java/com/example/ms_proveedor/MsProveedorApplicationTests.java
- ms-stock/src/test/java/example/ms_stock/MsStockApplicationTests.java

## Decisión aplicada en esta versión final

Se mantuvo como base la versión revisada anterior, se agregaron mejoras solicitadas por la pauta y se evitó incluir carpetas `target/` generadas.
