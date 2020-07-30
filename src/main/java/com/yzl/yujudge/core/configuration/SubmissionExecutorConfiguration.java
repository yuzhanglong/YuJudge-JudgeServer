package com.yzl.yujudge.core.configuration;

import com.yzl.yujudge.core.factory.SubmissionThreadPoolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhanglong
 * @description 自定义的判题线程池, 处理用户的提交
 * @date 2020-7-29 14:05:50
 * <p>
 * 队列最大容纳100个提交
 */

@Configuration
@EnableAsync
public class SubmissionExecutorConfiguration {
    final long KEEP_ALIVE_TIME = 5L;
    final int BLOCKING_QUEUE_CAPACITY = 100;
    final String THREAD_NAME_PREFIX = "submissionJudge";

    @Bean
    public ThreadPoolExecutor submissionAsyncServiceExecutor() {
        final int maximumPoolSize = Runtime.getRuntime().availableProcessors() + 1;
        return new ThreadPoolExecutor(
                maximumPoolSize,
                maximumPoolSize,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(BLOCKING_QUEUE_CAPACITY),
                new SubmissionThreadPoolFactory(THREAD_NAME_PREFIX),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
