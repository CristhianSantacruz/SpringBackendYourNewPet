FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu AS base
LABEL authors="klashz"
ADD build/libs/SpringBootAdoptedPaw.jar SpringBootAdoptedPaw.jar
ENTRYPOINT ["java", "-jar", "SpringBootAdoptedPaw.jar"]
EXPOSE 8080
# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/my-application.jar", "--spring.profiles.active=prod"]
