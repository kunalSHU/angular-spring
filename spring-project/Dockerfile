FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY src/main/resources/reactivestax.jks ./
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]