FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ADD target/beed.jar beed.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/beed.jar"]