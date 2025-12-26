package com.rath.spring.advanced.config.beanlifecycle;

import java.util.concurrent.atomic.AtomicInteger;

// simple shared step counter to show execution order in logs
final class OrderLogger {
    private static final AtomicInteger counter = new AtomicInteger(0);

    private OrderLogger() {
    }

    static void log(String message) {
        int step = counter.incrementAndGet();
        System.out.printf("[%02d] %s%n", step, message);
    }
}



