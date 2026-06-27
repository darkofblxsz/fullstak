# Dockerfile del modulo ms-cliente.
# Compila usando Maven + Java 21 dentro de Docker y luego ejecuta con Java 21.
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl ms-cliente -am clean package -DskipTests

FROM eclipse-temurin:21-jre
ENV TZ=America/Santiago
WORKDIR /app
COPY --from=build /workspace/ms-cliente/target/ms-cliente-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
