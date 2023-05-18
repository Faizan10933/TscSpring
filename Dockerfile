FROM openjdk:20
LABEL maintainer="faizan"
COPY target/tscspring-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
EXPOSE 15000
