FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios para ejecutar Maven Wrapper
COPY mvnw .
COPY pom.xml .

# Da permisos de ejecución al archivo mvnw
RUN chmod +x ./mvnw

# Copia todo el contenido del proyecto
COPY . .

# Compila y construye la aplicación
RUN ./mvnw clean package -DskipTests

# Expone el puerto 8083
EXPOSE 8083

# Copia el archivo JAR final para ejecutarlo
COPY target/*.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]