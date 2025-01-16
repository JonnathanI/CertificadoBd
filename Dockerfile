# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el Maven Wrapper y sus archivos
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# Hacer ejecutable el script de Maven Wrapper
RUN chmod +x ./mvnw

# Descargar dependencias sin compilar el proyecto
RUN ./mvnw dependency:go-offline -B

# Copiar el resto del código fuente
COPY src ./src

# Construir el proyecto
RUN ./mvnw clean package -DskipTests

# Copiar el JAR generado
COPY target/*.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
