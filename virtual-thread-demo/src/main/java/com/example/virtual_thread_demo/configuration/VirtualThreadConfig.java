package com.example.virtual_thread_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;

@Configuration
public class VirtualThreadConfig {

    @Bean
    public Executor taskExecutor() {
        // Tạo một Executor dựa trên Virtual Threads
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public Scheduler schedulerVirtualThread() {
        ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        return Schedulers.fromExecutor(virtualThreadExecutor);
    }
}