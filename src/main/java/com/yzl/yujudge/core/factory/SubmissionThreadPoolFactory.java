package com.yzl.yujudge.core.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义提交相关线程工厂
 *
 * @author yuzhanglong
 * @date 2020-7-29 14:07:52
 */

public class SubmissionThreadPoolFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    public SubmissionThreadPoolFactory(String name) {
        namePrefix = "From SubmissionFactory's " + name + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        return new Thread(null, task, name, 0);
    }
}
