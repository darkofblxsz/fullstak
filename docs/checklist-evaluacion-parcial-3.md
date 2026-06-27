# Checklist Evaluación Parcial 3

Este checklist fue preparado comparando el proyecto con la pauta de Evaluación Parcial 3.

## Entrega grupal

| Requisito | Estado | Evidencia en proyecto |
|---|---|---|
| Al menos 10 microservicios | Cumplido | Existen 10 microservicios de negocio más Eureka y Gateway |
| Patrón CSR | Cumplido parcial | Controller, service, repository y model separados |
| Eureka Server | Cumplido | Módulo `eureka-server` |
| API Gateway | Cumplido | Módulo `api-gateway` con rutas por servicio |
| Pruebas unitarias | Mejorado | Tests en `src/test/java` con JUnit y Mockito |
| Swagger/OpenAPI | Cumplido técnico | Dependencia springdoc en microservicios |
| YAML | Mejorado | `application.yml` agregado a servicios principales |
| Puesta en marcha sin Docker | Cumplido | `iniciar.bat` levanta Eureka, servicios y Gateway |
| README | Mejorado | README completo con puertos, rutas y pasos |
| Reportes de pruebas | Preparado | Se generan en `target/surefire-reports` al ejecutar Maven |
| Cobertura | Preparado | Plugin JaCoCo y `generar-cobertura.bat` |

## Orden de ejecución solicitado por el docente

1. Ejecutar `compilar.bat` para generar los JAR.
2. Ejecutar `iniciar.bat`.
3. Verificar Eureka: `http://localhost:8761`.
4. Verificar Gateway: `http://localhost:8080`.
5. Probar endpoints principales desde Postman.
6. Ejecutar `probar.bat` para mostrar pruebas unitarias.

## Riesgos pendientes

- La cobertura real del 80% solo se puede confirmar ejecutando JaCoCo en el computador del equipo.
- Se debe revisar que MySQL tenga todas las bases de datos creadas.
- Se deben reemplazar los nombres reales de todos los integrantes en el README.
- Si el profesor exige Docker en el examen transversal, se deben crear Dockerfile y docker-compose después.

## Preguntas probables de defensa

- ¿Qué función cumple Eureka?
- ¿Qué función cumple el API Gateway?
- ¿Por qué los controllers no deben tener lógica de negocio?
- ¿Qué hace Mockito en las pruebas unitarias?
- ¿Qué diferencia hay entre `@Mock` e `@InjectMocks`?
- ¿Dónde se generan los reportes de test?
- ¿Cómo se interpreta una falla de test?
- ¿Qué contiene el archivo YAML?
- ¿Cómo se levanta el sistema sin Docker?
