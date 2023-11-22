FROM maven:3.8.5-openjdk-17 AS build

COPY . .

RUN maven clean package -DskipTests


FROM openjdk:17-jdk-slim

COPY --from=build /target/agriculture-0.0.1-SNAPSHOT.jar agriculture.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "agriculture.jar"]