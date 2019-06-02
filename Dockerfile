FROM openjdk:8u212-jdk

RUN mkdir -p /opt/project/jar
COPY ./target/employee-management-0.0.1-SNAPSHOT.jar /opt/project/jar
CMD ["java", "-jar", "/opt/project/jar/employee-management-0.0.1-SNAPSHOT.jar"]
