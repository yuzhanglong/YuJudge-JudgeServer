#!/bin/sh

java -jar app.jar \
          --spring.redis.port=$REDIS_PORT \
          --spring.redis.host=$REDIS_HOST \
          --authorization.secret-key=$JUDGE_SERVER_USER_SECRET \
          --spring.datasource.url=$MYSQL_URL \
          --spring.datasource.username=$MYSQL_USER_NAME \
          --spring.datasource.password=$MYSQL_PASSWORD \
          --upload.qn-service.access-key=$QN_ACCESS_KEY \
          --upload.qn-service.secret-key=$QN_SECRET_KEY \
          --upload.qn-service.bucket=$QN_BUCKET