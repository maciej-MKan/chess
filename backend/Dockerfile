FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build

# base img for java 17
FROM eclipse-temurin:17

# work dir
WORKDIR /app

# coppy files
COPY --from=BUILD ./usr/app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]