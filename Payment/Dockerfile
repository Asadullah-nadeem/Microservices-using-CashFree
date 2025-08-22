FROM openjdk:21-jdk-slim
COPY target/product-service-*.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]
