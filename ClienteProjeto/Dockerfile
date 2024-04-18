# Usar a imagem base do Java (JDK)
FROM openjdk:11

# Argumento para passar a versão do artefato
ARG JAR_FILE=target/*.jar

# Copiar o artefato do build para o contêiner
COPY ${JAR_FILE} app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]