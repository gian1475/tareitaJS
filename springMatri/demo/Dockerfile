# Render no tiene runtime de Java, por eso el backend se sube con Docker

# 1) compilar el .jar
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q package -DskipTests

# 2) imagen final, solo con el JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# el plan gratis de Render tiene 512 MB de RAM
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75"
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
