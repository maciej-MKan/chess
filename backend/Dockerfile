FROM gradle:latest AS build
WORKDIR /usr/app/
COPY . .
RUN gradle clean build -x test

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build ./usr/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
