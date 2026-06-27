# Dockerfile del modulo eureka-server.
# Compila usando Maven + Java 21 dentro de Docker y luego ejecuta con Java 21.
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . .
RUN mvn -pl eureka-server -am clean package -DskipTests

FROM eclipse-temurin:21-jre
ENV TZ=America/Santiago
WORKDIR /app
COPY --from=build /workspace/eureka-server/target/eureka-server-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
