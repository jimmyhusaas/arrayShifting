FROM azul/zulu-openjdk:17-jre

WORKDIR /app

COPY target/shift-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]