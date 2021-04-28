FROM openjdk:11-jre-slim

WORKDIR /app
COPY ./build/libs/alphasoup-0.0.1-SNAPSHOT.jar /app/alphasoup-0.0.1.jar

EXPOSE 8080

CMD ["java", "-jar", "alphasoup-0.0.1.jar"]
