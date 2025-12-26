package com.rath.spring.advanced.config.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.rath.spring.advanced.config.beanlifecycle")
public class LifecycleConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public LifecycleBean lifecycleBean() {
        return new LifecycleBean();
    }

    @Bean
    public static LifecyclePostProcessor lifecyclePostProcessor() {
        return new LifecyclePostProcessor();
    }
}



