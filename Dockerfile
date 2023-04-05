FROM openjdk:17.0-slim
WORKDIR /app

COPY build/libs/news-write-server-0.0.1-SNAPSHOT.jar .

EXPOSE 8071
ENTRYPOINT [ "java", "-jar", "news-write-server-0.0.1-SNAPSHOT.jar" ] 