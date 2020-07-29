FROM openjdk:13.0.2
LABEL maintainer="Andrzej Kabat <andrzej.kabat@carbon-it.com>"

ADD ./build/libs/rest-api-kata.jar rest-api-kata.jar

EXPOSE 9090
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/rest-api-kata.jar"]