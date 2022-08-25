# build base image
FROM maven:3-openjdk-11-slim as maven

# copy pom.xml
COPY ./pom.xml ./pom.xml

# copy src
COPY ./src ./src

# build
RUN mvn package

# final base image
FROM openjdk:11-jre-slim

# Upgrade to latest patch versions of packages: https://pythonspeed.com/articles/security-updates-in-docker/
RUN apt-get update \
 && apt-get upgrade -y \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

# set deployment directory
WORKDIR /mod-spring-sample

# copy over the built artifact from the maven image
COPY --from=maven /target/mod-spring-sample*.jar ./mod-spring-sample.jar

# settings
ENV LOGGING_LEVEL_FOLIO='INFO'
ENV SERVER_PORT='9000'
ENV SPRING_DATASOURCE_PLATFORM='h2'
ENV SPRING_DATASOURCE_URL='jdbc:h2:./mod-spring-sample;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE'
ENV SPRING_DATASOURCE_DRIVERCLASSNAME='org.h2.Driver'
ENV SPRING_DATASOURCE_USERNAME='folio'
ENV SPRING_DATASOURCE_PASSWORD='folio'
ENV SPRING_H2_CONSOLE_ENABLED='true'
ENV SPRING_H2_WEBALLOW='true'
ENV SPRING_JPA_DATABASE_PLATFORM='org.hibernate.dialect.H2Dialect'
ENV TENANT_DEFAULT_TENANT='tern'
ENV TENANT_INITIALIZE_DEFAULT_TENANT='false'
ENV OKAPI_URL='http://localhost:9130'

#expose port
EXPOSE ${SERVER_PORT}

#run java command
CMD java -jar -Xmx4096m ./mod-spring-sample.jar \
  --logging.level.org.folio=${LOGGING_LEVEL_FOLIO} --server.port=${SERVER_PORT} --spring.activemq.broker-url=${SPRING_ACTIVEMQ_BROKER_URL} \
  --spring.datasource.platform=${SPRING_DATASOURCE_PLATFORM} --spring.datasource.url=${SPRING_DATASOURCE_URL} \
  --spring.datasource.driverClassName=${SPRING_DATASOURCE_DRIVERCLASSNAME} --spring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
  --spring.datasource.password=${SPRING_DATASOURCE_PASSWORD} --spring.jpa.database-platform=${SPRING_JPA_DATABASE_PLATFORM} \
  --spring.h2.console.enabled=${SPRING_H2_CONSOLE_ENABLED} --spring.h2.console.settings.web-allow-others=${SPRING_H2_WEBALLOW} \
  --tenant.default-tenant=${TENANT_DEFAULT_TENANT} --tenant.initialize-default-tenant=${TENANT_INITIALIZE_DEFAULT_TENANT} \
  --okapi.url=${OKAPI_URL}
