FROM openjdk:19-jdk-slim

VOLUME /tmp
COPY target/portfolio-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

#environment variables
ENV JAVA_OPTS=""

# Run the application when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]