FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_myTelegramBot
ENV BOT_TOKEN=1730710819:AAFD_Vjttt1oPFMEbHsDqgr3Bks_sD0Gek8
COPY ${JAR_FILE} app.jar
CMD ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}" , "-jar", "/app.jar"]
