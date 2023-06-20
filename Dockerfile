# define base docker image
FROM openjdk:17
EXPOSE 9004
# define code maintainer name
LABEL maintainer="Rupam"         
ADD target/leave-microservice.jar leave-microservice.jar
ENTRYPOINT ["java", "-jar", "/leave-microservice.jar"]
