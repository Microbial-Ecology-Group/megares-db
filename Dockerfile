FROM amazoncorretto:18-alpine3.14

# environment variable with default values
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_HOSTNAME=192.168.1.129
ENV POSTGRES_PORT=5432
ENV POSTGRES_DATABASE=amrdb

WORKDIR /var/meglab

ADD target/megares-1.0-SNAPSHOT.jar /var/meglab/app.jar
ADD config.yml /var/meglab/config.yml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "server", "config.yml"]
