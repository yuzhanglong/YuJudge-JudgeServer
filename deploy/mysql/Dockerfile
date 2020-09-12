FROM mysql:8.0
WORKDIR .
COPY  . .

RUN cp /yu-judge.sql /docker-entrypoint-initdb.d \
    && chmod 777 /entrypoint.sh