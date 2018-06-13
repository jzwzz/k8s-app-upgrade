FROM openjdk:8-jre-alpine

ENV JHIPSTER_SLEEP 2

# add directly the war
ADD *.jar /app.jar

RUN sh -c 'touch /app.war'
VOLUME /tmp
EXPOSE 8200
CMD echo "The application will start in ${JHIPSTER_SLEEP}s..." && \
    sleep ${JHIPSTER_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar -Dfile.encoding=UTF-8 -XX:+HeapDumpOnOutOfMemoryError -Xms128m -Xmx512m /app.jar
