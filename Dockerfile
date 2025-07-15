FROM amazoncorretto:17 AS builder
WORKDIR application
ARG JAR_FILE=build/libs/person-service.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM amazoncorretto:17
ENV PORT=8080
ARG REMOTE_DEBUG=false
ENV REMOTE_DEBUG_ENABLED=$REMOTE_DEBUG
WORKDIR application
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["sh", "-c", "java ${REMOTE_DEBUG_ENABLED:+-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005} org.springframework.boot.loader.launch.JarLauncher"]
