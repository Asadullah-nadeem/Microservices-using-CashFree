FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .

RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/payment-service-*.jar payment-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/payment-service.jar"]
