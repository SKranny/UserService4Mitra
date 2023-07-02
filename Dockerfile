FROM openjdk:17

WORKDIR /build

ARG SECRET_KEY=secret

ADD /target/UserService-0.0.1-SNAPSHOT.jar ./UserService.jar

EXPOSE 8082

CMD java -jar UserService.jar