package com.yzl.yujudge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yuzhanglong
 * @version develop
 * @description YuJudge的服务端项目
 */

@SpringBootApplication
@EnableScheduling
public class YuJudgeJudgeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YuJudgeJudgeServerApplication.class, args);
    }
}