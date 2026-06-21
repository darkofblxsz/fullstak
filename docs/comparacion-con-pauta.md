# Comparación del proyecto con la pauta

## Puntos fuertes encontrados

- El proyecto tiene estructura multi-módulo con Maven.
- Existen más de 10 servicios contando Eureka y Gateway.
- El API Gateway tiene rutas configuradas hacia los microservicios de negocio.
- Los microservicios tienen estructura por capas.
- Se agregaron pruebas unitarias en `src/test/java`.
- Se agregaron scripts `.bat` para compilar, probar, generar cobertura y levantar el sistema.
- Se agregó documentación de ejecución en README.

## Cambios aplicados

- README completo para cumplir forma de entrega.
- Archivos `application.yml` para mejorar cumplimiento del requisito YAML.
- `compilar.bat` para generar JARs.
- `probar.bat` para ejecutar pruebas unitarias.
- `generar-cobertura.bat` para evidenciar cobertura con JaCoCo.
- Nuevas pruebas de service para validar reglas de negocio y uso de Mockito.
- Checklist de defensa y entrega.

## Pendiente de verificar en computador local

- Ejecutar `mvnw.cmd clean test`.
- Ejecutar `mvnw.cmd clean verify` para revisar cobertura.
- Levantar MySQL y crear bases de datos.
- Ejecutar `compilar.bat` e `iniciar.bat`.
- Confirmar en Eureka que todos los servicios aparezcan como `UP`.
- Confirmar en Swagger que cada microservicio documente sus endpoints.
