FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD essence-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
