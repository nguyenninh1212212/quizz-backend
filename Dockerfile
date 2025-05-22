# Stage 1: Build app bằng Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Tải dependency trước cho cache nhanh
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY . .
COPY .env /app/.env

# Build ra file JAR
RUN mvn clean package -DskipTests

# Stage 2: Image runtime
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
