FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/beed.jar beed.jar
EXPOSE 8080
CMD ["java","-jar","beed.jar"]