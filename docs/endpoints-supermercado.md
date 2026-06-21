# Endpoints principales del supermercado

Swagger centralizado:

- http://localhost:8080/doc/swagger-ui.html

Swagger individual:

- Producto: http://localhost:8086/doc/swagger-ui.html
- Stock: http://localhost:8083/doc/swagger-ui.html
- Categoria: http://localhost:8090/doc/swagger-ui.html
- Proveedor: http://localhost:8084/doc/swagger-ui.html
- Cliente: http://localhost:8089/doc/swagger-ui.html
- Carrito: http://localhost:8091/doc/swagger-ui.html
- Pedido: http://localhost:8081/doc/swagger-ui.html
- Pago: http://localhost:8082/doc/swagger-ui.html
- Envio: http://localhost:8088/doc/swagger-ui.html
- Boleta: http://localhost:8092/doc/swagger-ui.html

Ejemplo producto:

```json
{
  "nombre": "Notebook Lenovo",
  "descripcion": "Notebook para oficina",
  "precio": 450000,
  "stock": 10,
  "categoria": "Tecnologia",
  "marca": "Lenovo",
  "categoriaId": 1,
  "proveedorId": 1,
  "codigoBarras": "7800000000012"
}
```

El proyecto queda pensado para demostrar un flujo real de supermercado: catalogo, inventario, carrito, pedido, pago, envio y boleta.
