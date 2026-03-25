FROM openjdk:17-slim-buster as builder

MAINTAINER G.MHANDU

COPY target/premium-receipting-service.jar /opt/premium-receipting-service.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar", "premium-receipting-service.jar"]
