FROM adoptopenjdk/openjdk11:alpine-jre
ARG APP_NAME="blog"
ARG JAR_FILE="/target/${APP_NAME}.jar"

COPY /target/*.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]

EXPOSE 8010