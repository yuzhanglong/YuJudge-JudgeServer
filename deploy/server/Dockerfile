FROM java:8
WORKDIR .
COPY  . .

# 运行环境的初始化
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' > /etc/timezone \
    && chmod 777 /entrypoint.sh

# 暴露8080端口
EXPOSE 8081
ENTRYPOINT ["./entrypoint.sh"]