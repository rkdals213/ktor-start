FROM openjdk:11
EXPOSE 8080
ADD build/libs/ktor-start-0.0.1-all.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]