# syntax=docker/dockerfile:1

# -------- Build stage --------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Cache dependencies first
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
RUN chmod +x mvnw || true
RUN ./mvnw -q -DskipTests dependency:go-offline

# Now copy sources and build
COPY src ./src
RUN ./mvnw -q -DskipTests package

# -------- Run stage --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Render sets PORT; Spring reads it via application-prod.properties -> server.port=${PORT:8080}
ENV SPRING_PROFILES_ACTIVE=prod

COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
