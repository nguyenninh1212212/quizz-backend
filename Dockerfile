# Multi-stage build với Maven 3.9.9 và Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom.xml và tải dependency trước để tận dụng cache Docker layers
COPY pom.xml .

# Tải dependencies offline cho nhanh, bao gồm cả Lombok
RUN mvn dependency:go-offline -B

# Copy toàn bộ source code
COPY src ./src

# Build với annotation processing EXPLICIT và đồng bộ version
RUN mvn clean package -DskipTests \
    -Dmaven.compiler.source=21 \
    -Dmaven.compiler.target=21 \
    -Dmaven.compiler.proc=full \
    --batch-mode

# ---
# Runtime stage sử dụng JRE thay vì JDK để giảm size
FROM eclipse-temurin:21-jre-jammy AS runner

# Tạo user non-root cho security
RUN groupadd -r appuser && useradd -r -g appuser appuser

WORKDIR /app

# Copy jar từ builder stage
COPY --from=builder /app/target/*.jar app.jar

# Change ownership
RUN chown appuser:appuser app.jar

# Switch to non-root user
USER appuser

EXPOSE 8080

# Health check (optional)
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]