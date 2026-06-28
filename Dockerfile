# Dockerfile multi-etapa para cualquier modulo Maven del proyecto.
# No requiere Java ni Maven instalados en el PC anfitrion.

FROM maven:3.9.9-eclipse-temurin-21 AS build
ARG MODULE
WORKDIR /workspace
COPY pom.xml ./
COPY eureka-server ./eureka-server
COPY api-gateway ./api-gateway
COPY ms-boleta ./ms-boleta
COPY ms-carrito ./ms-carrito
COPY ms-categoria ./ms-categoria
COPY ms-cliente ./ms-cliente
COPY ms-envio ./ms-envio
COPY ms-pago ./ms-pago
COPY ms-pedido ./ms-pedido
COPY ms-producto ./ms-producto
COPY ms-proveedor ./ms-proveedor
COPY ms-stock ./ms-stock
RUN mvn -pl ${MODULE} -am clean package -DskipTests

FROM eclipse-temurin:21-jre
ARG MODULE
ENV TZ=America/Santiago
WORKDIR /app
COPY --from=build /workspace/${MODULE}/target/${MODULE}-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8080 8081 8082 8083 8084 8086 8088 8089 8090 8091 8092 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
