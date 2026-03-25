FROM openjdk:17-slim-buster as builder

MAINTAINER G.MHANDU

COPY target/cash-collection-service.jar /opt/cash-collection-service.jar

WORKDIR /opt

ENTRYPOINT ["java", "-jar", "cash-collection-service.jar"]