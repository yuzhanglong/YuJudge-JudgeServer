#!/bin/sh

JUDGE_SERVER_IMAGE="judge-server:1.0";
MYSQL_IMAGE="mysql:8.0";

function upload() {
  docker tag "$1" registry-vpc.cn-shenzhen.aliyuncs.com/coderyzl/"$1";
  docker push registry-vpc.cn-shenzhen.aliyuncs.com/coderyzl/"$1";
  echo ""$1" upload successfully";
}

# 构建mysql
cd mysql;
docker build -t "$MYSQL_IMAGE" .;
echo "mysql build successfully";
upload "$MYSQL_IMAGE";

# 构建judgeServer
cd ..
cd server
docker build -t "$JUDGE_SERVER_IMAGE" .
echo "judgeServer build successfully";
upload "$JUDGE_SERVER_IMAGE";