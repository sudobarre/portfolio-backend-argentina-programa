# Use official OpenJDK 19 image as base image
FROM openjdk:19-jdk-alpine

# Set environment variables
ARG DB_PASSWORD
ARG JWT_SECRET
ARG MAIL_USERNAME
ARG MAIL_PASSWORD
ARG DB_HOST

ENV DB_HOST=${DB_HOST}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV JWT_SECRET=${JWT_SECRET}
ENV MAIL_USERNAME=${MAIL_USERNAME}
ENV MAIL_PASSWORD=${MAIL_PASSWORD}

# Set the working directory to /app
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the project
RUN ./mvnw clean package

# Copy the jar file to the container
#COPY target/portfolio-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "target/portfolio-0.0.1-SNAPSHOT.jar"]
