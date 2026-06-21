# Revision de Swagger / OpenAPI

Swagger fue agregado a los microservicios de negocio usando `springdoc-openapi-starter-webmvc-ui`, siguiendo la guia entregada.

## Ruta configurada

Cada microservicio expone Swagger en:

```text
http://localhost:PUERTO/doc/swagger-ui.html
```

Tambien se puede validar el JSON OpenAPI en:

```text
http://localhost:PUERTO/v3/api-docs
```

## Puertos para probar

| Microservicio | Puerto | Swagger |
|---|---:|---|
| ms-pedido | 8081 | http://localhost:8081/doc/swagger-ui.html |
| ms-pago | 8082 | http://localhost:8082/doc/swagger-ui.html |
| ms-stock | 8083 | http://localhost:8083/doc/swagger-ui.html |
| ms-proveedor | 8084 | http://localhost:8084/doc/swagger-ui.html |
| ms-producto | 8086 | http://localhost:8086/doc/swagger-ui.html |
| ms-envio | 8088 | http://localhost:8088/doc/swagger-ui.html |
| ms-cliente | 8089 | http://localhost:8089/doc/swagger-ui.html |
| ms-categoria | 8090 | http://localhost:8090/doc/swagger-ui.html |
| ms-carrito | 8091 | http://localhost:8091/doc/swagger-ui.html |
| ms-boleta | 8092 | http://localhost:8092/doc/swagger-ui.html |

## Pasos de revision

1. Encender XAMPP y MySQL.
2. Ejecutar `compilar.bat` o Maven con `clean package`.
3. Ejecutar `iniciar.bat`.
4. Abrir Eureka: http://localhost:8761.
5. Confirmar que todos los servicios esten en estado UP.
6. Abrir alguna URL de Swagger, por ejemplo: http://localhost:8083/doc/swagger-ui.html.
7. Probar un endpoint desde el boton `Try it out`.

## Nota

El API Gateway funciona en el puerto 8080 para enrutar peticiones, pero la documentacion Swagger fue configurada en cada microservicio de negocio.
