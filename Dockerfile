FROM amazoncorretto:11-alpine-jdk
MAINTAINER LeoCastillo
COPY target/LeoCastillo-0.0.1-SNAPSHOT.jar LeoCastillo.jar
ENTRYPOINT ["java","-jar","/LeoCastillo.jar"]
EXPOSE 8080