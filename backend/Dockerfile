FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle clean build -x test

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=BUILD ./usr/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]