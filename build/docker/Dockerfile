# STEP 1 build the maven artefacts
FROM maven:3.6.3-openjdk-14-slim as builder

COPY . /usr/src/app

RUN mvn -f /usr/src/app/ clean verify

# STEP 2 create the docker image
FROM openjdk:8u252-jre-slim

# command need to be set from outside e.g. to /opt/app/run-java.sh
CMD ["cat", "/IMAGE_INFO"]

# No entrypoint set
ENTRYPOINT []

# Exposed default port
EXPOSE 8080

# Copy local folders to docker container
COPY build/docker /

COPY --from=builder /usr/src/app/application/target/*.jar /opt/app

# Fix permissions on run-java.sh
RUN chmod 0755 /opt/app/run-java.sh