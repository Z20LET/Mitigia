FROM openjdk:23

WORKDIR /app

COPY target/mitigia-0.0.3-SNAPSHOT.jar /app/mitigia.jar

EXPOSE 8080

CMD ["java", "-jar", "mitigia.jar"]