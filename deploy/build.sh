#!/bin/sh

# 构建编译所需要的文件

cd ..

rm -rf build

mkdir build

cp -r deploy/* build/
cp target/YuJudge-JudgeServer-1.0.0.jar build/server/app.jar