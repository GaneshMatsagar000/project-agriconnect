FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/agriculture-0.0.1-SNAPSHOT.jar agriculture.jar
COPY src/main/resources/application.properties /app/src/main/resources/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "agriculture.jar"]
