FROM openjdk:17
EXPOSE 9004
LABEL maintainer="RupSTLer"          
ADD target/leave-microservice.jar leave-microservice.jar
ENTRYPOINT ["java", "-jar", "leave-microservice.jar"]