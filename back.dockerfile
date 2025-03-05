FROM openjdk:21-jdk-slim
ARG JAR_FILE=backend/target/financial-platform-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_wallex.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app_wallex.jar"]