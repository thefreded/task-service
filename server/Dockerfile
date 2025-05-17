FROM eclipse-temurin:21-jdk-alpine

WORKDIR /deployments

# Copy files from the repository
COPY ./server/target/quarkus-app/lib/ /deployments/lib/
COPY ./server/target/quarkus-app/app/ /deployments/app/
COPY ./server/target/quarkus-app/quarkus/ /deployments/quarkus/
COPY ./server/target/quarkus-app/quarkus-run.jar /deployments/quarkus-run.jar

EXPOSE 8085

ENV JAVA_OPTIONS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "quarkus-run.jar"]