# Etapa de build
FROM maven:3.8.4-openjdk-11 AS builder

# Define the working directory in the container
WORKDIR /app

# Copy the source code and the pom.xml file into the container
COPY src /app/src
COPY pom.xml /app

# Run Maven build
RUN mvn clean package

# Etapa de execução
FROM openjdk:11

# Define the working directory in the container
WORKDIR /app

# Copy the built jar file from the builder stage into the container
# Make sure the path matches the location where the jar file is created
COPY --from=builder /app/target/*.jar /app.jar

# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]


## Usar a imagem base do Java (JDK)
#FROM openjdk:11
#
## Argumento para passar a versão do artefato
#ARG JAR_FILE=target/*.jar
#
## Copiar o artefato do build para o contêiner
#COPY ${JAR_FILE} app.jar
#
## Comando para executar a aplicação
#ENTRYPOINT ["java","-jar","/app.jar"]