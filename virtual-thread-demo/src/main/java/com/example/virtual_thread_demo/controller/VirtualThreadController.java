package com.example.virtual_thread_demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class VirtualThreadController {

    private final Scheduler scheduler;

    @GetMapping("/virtual-thread")
    public Mono<Object> handleRequest() {
        return Mono.fromCallable(() -> {
            // Công việc nặng sẽ chạy trên Virtual Threads
            Thread.sleep(1000);
            log.info("Handled by Virtual Thread: {}", Thread.currentThread());
            Object a = Map.of("resultCode", 200,
                    "data", Map.of("value", "ok"));
            return a;
        }).subscribeOn(scheduler);
    }
}
