package com.jeuni.example.springboot.inspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        log.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // 병렬처리 쓰레드 개수
        executor.setMaxPoolSize(4);
        executor.setKeepAliveSeconds(10);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setThreadNamePrefix("Executor-");
        executor.initialize();

        return executor;
    }
}
