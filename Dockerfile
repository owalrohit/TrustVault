# Build React frontend
FROM node:18-alpine AS frontend
WORKDIR /frontend
COPY frontend/ .
RUN npm install && npm run build

# Build Spring Boot backend
FROM maven:3.9.4-eclipse-temurin-17 AS backend
WORKDIR /backend
COPY backend/ .
RUN mvn clean package -DskipTests

# Final image
FROM eclipse-temurin:17-jdk-alpine
COPY --from=frontend /frontend/build /app/static
COPY --from=backend /backend/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
