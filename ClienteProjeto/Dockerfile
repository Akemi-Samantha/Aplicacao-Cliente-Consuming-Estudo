# Etapa 1: Construir o projeto usando Maven
FROM maven:3.8.4-openjdk-11 as build

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar os arquivos pom.xml e o diretório src para o contêiner
COPY pom.xml .
COPY src ./src

# Compilar o projeto e empacotar o artefato
RUN mvn clean package

# Etapa 2: Criar a imagem final com o JRE para executar a aplicação
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o artefato da etapa de construção para o diretório de trabalho atual
COPY --from=build /app/target/*.jar app.jar

# Definir o comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


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