FROM openjdk:11
ADD target/payconiq-0.0.1-SNAPSHOT.jar payconiq-0.0.1-SNAPSHOT.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "payconiq-0.0.1-SNAPSHOT.jar"]
