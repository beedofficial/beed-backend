FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/beed-backend.jar beed-backend.jar
EXPOSE 8080
CMD ["java","-jar","beed-backend.jar"]