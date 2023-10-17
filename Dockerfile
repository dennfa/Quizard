FROM openjdk:17
LABEL maintainer="dennfa@example.com"
EXPOSE 8080

ADD backend/target/quizard.jar quizard.jar

CMD [ "sh", "-c", "java -jar /quizard.jar" ]