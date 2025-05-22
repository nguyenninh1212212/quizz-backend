FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml và tải dependency trước để tận dụng cache Docker layers
COPY pom.xml .

# Tải dependencies offline cho nhanh
RUN mvn dependency:go-offline -B

# Copy toàn bộ source code
COPY src ./src

# Build với annotation processing đảm bảo, skip tests cho nhanh
RUN mvn clean package -DskipTests -Dmaven.compiler.source=21 -Dmaven.compiler.target=21

# ---

FROM openjdk:21-jdk AS runner

WORKDIR /app

# Copy jar từ builder
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
