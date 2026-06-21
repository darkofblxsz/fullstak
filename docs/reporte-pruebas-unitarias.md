# Reporte simple de pruebas unitarias

Este documento resume las pruebas unitarias agregadas siguiendo la guía del ramo.

## Regla aplicada

Las pruebas fueron creadas en `src/test/java` y no en `src/main/java`.
Se reemplazaron los tests automáticos `*ApplicationTests.java` de los microservicios de negocio para evitar levantar Spring Boot completo, MySQL, Eureka o Gateway.

## Microservicios con pruebas agregadas

- `ms-boleta`: `BoletaControllerTest`
- `ms-carrito`: `CarritoControllerTest`
- `ms-categoria`: `CategoriaControllerTest`
- `ms-cliente`: `ClienteControllerTest`
- `ms-envio`: `EnvioControllerTest`
- `ms-pago`: `PagoControllerTest`
- `ms-pedido`: `PedidoControllerTest`
- `ms-producto`: `ProductoControllerTest`
- `ms-proveedor`: `ProveedorControllerTest`
- `ms-stock`: `StockControllerTest` y `StockServiceTest`

## Correcciones aplicadas durante la revisión

- Se corrigieron controllers que usaban `org.apache.http.HttpStatus` para usar `org.springframework.http.HttpStatus`.
- Se cambió `HttpStatus.SC_CREATED` por `HttpStatus.CREATED`.
- Se corrigió `ClienteServiceImpl`, porque el repository estaba declarado como `null`.
- Se eliminó una anotación propia `Service.java` en `ms-proveedor` que podía confundirse con `org.springframework.stereotype.Service`.
- Se verificó que los tests estén ubicados en la carpeta correcta y que no queden archivos `*ApplicationTests.java` automáticos.

## Casos positivos cubiertos

- Crear recurso con POST y esperar HTTP 201 Created.
- Listar recursos con GET y esperar HTTP 200 OK.
- Eliminar recurso con DELETE y esperar HTTP 204 No Content.
- Validar stock disponible.
- Descontar stock correctamente.

## Caso hipotético de falla para QA

Ejemplo:

- Microservicio: `ms-producto`
- Clase probada: `ProductoController`
- Test: `guardar_conDatosValidos_deberiaRetornar201`
- Resultado esperado: HTTP 201 Created
- Resultado obtenido: HTTP 200 OK
- Observación: el producto puede crearse, pero el endpoint no respeta el código HTTP esperado para creación de recursos.
- Sugerencia: revisar el método POST y retornar `ResponseEntity.status(HttpStatus.CREATED)`.

## Comandos para ejecutar

Desde la raíz del proyecto padre:

```bash
mvn test -pl ms-boleta
mvn test -pl ms-carrito
mvn test -pl ms-categoria
mvn test -pl ms-cliente
mvn test -pl ms-envio
mvn test -pl ms-pago
mvn test -pl ms-pedido
mvn test -pl ms-producto
mvn test -pl ms-proveedor
mvn test -pl ms-stock
```

Ejecutar un test específico:

```bash
mvn -pl ms-stock -Dtest=StockServiceTest test
```

## Reportes Maven

Después de ejecutar, revisar:

```text
microservicio/target/surefire-reports/
```

Ahí se ven `Tests run`, `Failures`, `Errors` y el detalle del test fallido.

## Nota importante

No se pudo ejecutar `mvn test` dentro del entorno de revisión porque Maven no está instalado aquí y el proyecto no incluye wrapper `mvnw` ni carpeta `.mvn/wrapper`.
