FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/agriculture-0.0.1-SNAPSHOT.jar agriculture.jar
COPY src/main/resources/application.properties /agriculture/src/main/resources/
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "agriculture.jar"]
