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


FROM ubuntu:latest
MAINTAINER sudobarre
COPY target/portfolio /app/portfolio
RUN apt-get -y update
RUN apt-get -y install build-essential
EXPOSE 8080
CMD ["/app/portfolio"]
