FROM openjdk:20-jdk-alpine
EXPOSE 8000
# ARG JAR_FILE=target/demo-app-1.0.0.jar
# ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
