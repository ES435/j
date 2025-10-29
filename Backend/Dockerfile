# --- Build stage ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# pom.xml zuerst kopieren und Abhängigkeiten laden
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Quellcode kopieren und Projekt bauen
COPY src ./src
RUN mvn -q -DskipTests package

# --- Runtime stage ---
FROM eclipse-temurin:21-jre
WORKDIR /app

# Das gebaute JAR aus dem Build-Stage kopieren
COPY --from=build /app/target/*.jar app.jar

# Port für Spring Boot öffnen
EXPOSE 8080

# Startbefehl
ENTRYPOINT ["java", "-jar", "app.jar"]