FROM tomcat:9-jre8
MAINTAINER Aleksandr Efremov <topefremov@yandex.ru>

ARG WAR_FILE
COPY target/${WAR_FILE} /usr/local/tomcat/webapps/${WAR_FILE}
COPY scripts/wait-for-postgres.sh /usr/local/tomcat/wait-for-postgres.sh
RUN chmod 550 /usr/local/tomcat/wait-for-postgres.sh && \
    sed -i -e 's/\r$//' /usr/local/tomcat/wait-for-postgres.sh && \
    apt-get update && apt-get install -y postgresql-client

ENV SPRING_PROPERTIES ""

RUN echo 'JAVA_OPTS="$JAVA_OPTS $SPRING_PROPERTIES"' >> /usr/local/tomcat/bin/setenv.sh

CMD ["catalina.sh", "run"]
