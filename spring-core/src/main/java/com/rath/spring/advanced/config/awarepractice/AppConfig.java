package com.rath.spring.advanced.config.awarepractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.rath.spring.advanced.config.awarepractice")
public class AppConfig {
    @Bean("Alpha1")
    public Alpha alphaOne() {
        return new Alpha("Alpha1");
    }

    @Bean("Alpha2")
    public Alpha alphaTwo() {
        return new Alpha("Alpha2");
    }
}
