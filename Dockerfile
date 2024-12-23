# Utiliza una imagen base con Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8081 (o el que uses en Spring Boot)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
