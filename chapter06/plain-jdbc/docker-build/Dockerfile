FROM mysql:5.7.37

ENV MYSQL_ROOT_PASSWORD=mypass

ENV MYSQL_USER=prospring5
ENV MYSQL_PASSWORD=prospring5
ENV MYSQL_DATABASE=musicdb

COPY ./scripts/ /docker-entrypoint-initdb.d/

EXPOSE 3306

