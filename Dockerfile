FROM adoptopenjdk/openjdk17:alpine-jre

EXPOSE 8080

COPY linkShorter-0.0.1-SNAPSHOT.jar shorlink.jar

CMD ["java", "-jar", "shorlink.jar"]