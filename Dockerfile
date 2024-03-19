FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu AS base
LABEL authors="klashz"
EXPOSE 8080
ADD build/libs/SpringBootAdoptedPaw.jar SpringBootAdoptedPaw.jar
ENTRYPOINT ["java", "-jar", "SpringBootAdoptedPaw.jar"]