# Construir el proyecto usando Maven
FROM maven:3.9.8-eclipse-temurin-22 AS build

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests=true

# Construcción de la imagen final
FROM openjdk:22-jdk

# Copiar el JAR desde la etapa de construcción al contenedor final
COPY --from=build /app/target/*.jar /springboot-mongo-docker.jar

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/springboot-mongo-docker.jar"]
