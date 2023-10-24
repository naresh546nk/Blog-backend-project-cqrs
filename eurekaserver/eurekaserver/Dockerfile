FROM adoptopenjdk/openjdk11:alpine-jre
ARG APP_NAME="eurekaserver"
ARG JAR_FILE="/target/${APP_NAME}.jar"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]

EXPOSE 8761