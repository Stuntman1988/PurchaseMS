FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/PurchaseMS.jar
COPY ${JAR_FILE} PurchaseMS.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/PurchaseMS.jar"]