# FROM ubuntu:latest AS build
# RUN apt-get update
# RUN apt-get install openjdk-11-jdk -y
# COPY . .

# RUN ./gradlew bootJar --no-daemon

# FROM openjdk:11-jdk-slim

# EXPOSE 8080

# COPY --from=build /build/libs/jaguars-0.0.1-SNAPSHOT.jar app.jar

# ENTRYPOINT ["java", "-jar", "app.jar"]


FROM gradle:6-jdk11
COPY ./ ./
RUN chmod 777 ./gradlew
RUN ./gradlew build -x test
ARG SAMPLE_FOLDER
ARG CHANNEL_TOKEN
ARG CHANNEL_SECRET
CMD java -jar ./build/libs/jaguars-0.0.1-SNAPSHOT.jar