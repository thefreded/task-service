FROM eclipse-temurin:21-jdk-alpine

WORKDIR /deployments

COPY server/target/quarkus-app/lib/ ./lib/
COPY server/target/quarkus-app/app/ ./app/
COPY server/target/quarkus-app/quarkus-run.jar ./quarkus-run.jar

EXPOSE 8085

ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "quarkus-run.jar"]
