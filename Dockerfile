FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

COPY target/linkShorter-1.0.jar shorlink.jar

CMD ["java", "-jar", "shorlink.jar"]