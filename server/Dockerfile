FROM eclipse-temurin:21-jdk-alpine

WORKDIR /deployments

COPY server/target/quarkus-app/lib/ /deployments/lib/
COPY server/target/quarkus-app/*.jar /deployments/
COPY server/target/quarkus-app/app/ /deployments/app/
COPY server/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8085

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "quarkus-run.jar"]


