FROM openjdk:8-alpine

ADD target/*.class BackendInventarioApplication.class

COPY . /BackendInventarioApplication

ENTRYPOINT [ "java", "-jar", "BackendInventarioApplication.class" ]