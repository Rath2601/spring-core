package com.rath.spring.advanced.config.awarepractice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            context.getBean("Alpha1", Alpha.class);
            context.getBean("Alpha2", Alpha.class);
            System.out.println("Aware interfaces demo complete.");
        }
    }
}
