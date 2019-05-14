FROM gradle:5.4.1-jdk8

WORKDIR /app

COPY src /app/src
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY ddl /app/ddl

USER root

RUN ls -a

# RUN gradle tasks --stacktrace

# RUN gradle build --stacktrace

# RUN gradle test --stacktrace

EXPOSE 8080

CMD ["gradle", "bootRun"]
