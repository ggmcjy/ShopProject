FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/torderproject-1.0.jar ToderProject.jar
ENTRYPOINT ["java","-jar","ToderProject.jar"]