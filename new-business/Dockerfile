FROM openjdk:17-slim-buster as builder

MAINTAINER G.MHANDU

ADD target/life-web-form-dashboard.jar  /opt/life-web-form-dashboard.jar

WORKDIR /opt

COPY . /opt

ENTRYPOINT ["java", "-jar", "life-web-form-dashboard.jar", "&"]