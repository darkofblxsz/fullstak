# Documentacion Swagger / OpenAPI

Se agrego la configuracion de Swagger/OpenAPI siguiendo la guia practica entregada por el docente.

## Cambios aplicados

- Dependencia `springdoc-openapi-starter-webmvc-ui` en los microservicios de negocio.
- Propiedades `springdoc.api-docs.enabled=true`, `springdoc.swagger-ui.enabled=true` y ruta personalizada `/doc/swagger-ui.html`.
- Clase `SwaggerConfig` en paquete `config` de cada microservicio.
- Anotaciones basicas `@Tag`, `@Operation`, `@ApiResponse` y `@ApiResponses` en los controladores.

## URLs para revisar Swagger

Con los servicios levantados mediante `iniciar.bat`, abrir las siguientes rutas:

- Pedido: http://localhost:8081/doc/swagger-ui/index.html
- Pago: http://localhost:8082/doc/swagger-ui/index.html
- Stock: http://localhost:8083/doc/swagger-ui/index.html
- Proveedor: http://localhost:8084/doc/swagger-ui/index.html
- Producto: http://localhost:8086/doc/swagger-ui/index.html
- Envio: http://localhost:8088/doc/swagger-ui/index.html
- Cliente: http://localhost:8089/doc/swagger-ui/index.html
- Categoria: http://localhost:8090/doc/swagger-ui/index.html
- Carrito: http://localhost:8091/doc/swagger-ui/index.html
- Boleta: http://localhost:8092/doc/swagger-ui/index.html

## Evidencia para la defensa

Para la defensa tecnica se recomienda mostrar un microservicio en Swagger, ejecutar un endpoint GET y explicar que Swagger documenta rutas, parametros, codigos de respuesta y modelos de datos.
