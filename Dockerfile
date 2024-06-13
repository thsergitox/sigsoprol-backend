# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo .jar de la aplicación al contenedor
COPY target/241CC341SpringSigsoprolBackend-0.0.1-SNAPSHOT.jar app.jar

# Configurar las variables de entorno
ENV SPRING_CONFIG_IMPORT=optional:file:.env[.properties]

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
