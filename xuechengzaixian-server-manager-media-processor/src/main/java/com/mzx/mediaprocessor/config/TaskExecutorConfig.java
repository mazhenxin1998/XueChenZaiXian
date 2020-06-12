package com.mzx.mediaprocessor.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.*;

/**
 * @author ZhenXinMa
 * @date 2020/5/18 18:35
 */
@Configuration
public class TaskExecutorConfig implements AsyncConfigurer {

    /**
     * 配置Spring的线程池.
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        // 8 10 400
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 8, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(400));
        // 当队列满的时候那么将会抛出异常.
        // 那么问题来 抛出异常应该从哪里捕获？
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
