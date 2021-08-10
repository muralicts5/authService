FROM amazoncorretto:11
EXPOSE 8200
ADD target/hi-service-0.0.1-SNAPSHOT.jar hi.jar 
ENTRYPOINT ["java","-jar","/hi.jar"]