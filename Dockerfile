FROM openjdk:17-jdk-slim
WORKDIR /app
VOLUME /tmp
COPY target/app.jar app.jar
ENV JAVA_OPTS=""
ENV HOST_DB=""
ENV DB=""
ENV DB_USER=""
ENV DB_PWD=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]