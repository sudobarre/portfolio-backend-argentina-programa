FROM ubuntu:latest
MAINTAINER sudobarre
COPY target/portfolio /app/portfolio
RUN apt-get -y update
RUN apt-get -y install build-essential
EXPOSE 8080
CMD ["/app/portfolio"]
