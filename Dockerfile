# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copy Maven wrapper and pom first (layer caching)
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw

# Download dependencies (cached unless pom changes)
RUN ./mvnw dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN ./mvnw package -DskipTests -B

# ─── Runtime image ───────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Create a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
