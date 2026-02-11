# ----------------------------------------
# ETAPA 1: CONSTRUCCIÓN (BUILD)
# Usamos una imagen con Maven y Java 21
# ----------------------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos todo tu código al contenedor
COPY . .

# Compilamos el proyecto y creamos el .jar (saltando los tests para ir rápido)
RUN mvn clean package -DskipTests

# ----------------------------------------
# ETAPA 2: EJECUCIÓN (RUN)
# Usamos una imagen ligera solo con Java 21 (JRE) para que pese menos
# ----------------------------------------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
# OJO: Aquí uso el nombre exacto que genera tu pom.xml (pedido-0.0.1-SNAPSHOT.jar)
COPY --from=build /app/target/pedido-0.0.1-SNAPSHOT.jar app.jar

# Le decimos a Render que este puerto es el bueno
EXPOSE 8080

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]