# Swagger publico y centralizado

## Acceso publico
Se quitaron las dependencias de Spring Security de los microservicios de negocio para que Swagger no solicite usuario ni contrasena.

## Swagger individual por microservicio
- MS Pedido: http://localhost:8081/doc/swagger-ui.html
- MS Pago: http://localhost:8082/doc/swagger-ui.html
- MS Stock: http://localhost:8083/doc/swagger-ui.html
- MS Proveedor: http://localhost:8084/doc/swagger-ui.html
- MS Producto: http://localhost:8086/doc/swagger-ui.html
- MS Envio: http://localhost:8088/doc/swagger-ui.html
- MS Cliente: http://localhost:8089/doc/swagger-ui.html
- MS Categoria: http://localhost:8090/doc/swagger-ui.html
- MS Carrito: http://localhost:8091/doc/swagger-ui.html
- MS Boleta: http://localhost:8092/doc/swagger-ui.html

## Swagger centralizado desde API Gateway
Tambien se configuro un Swagger UI central en el API Gateway:

http://localhost:8080/doc/swagger-ui.html

En esa pantalla debe aparecer un selector con las documentaciones OpenAPI de los microservicios.

## Importante
Para que el Swagger central funcione, Eureka, los microservicios y el API Gateway deben estar levantados.
