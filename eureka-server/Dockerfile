FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/eureka-server-*.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]
