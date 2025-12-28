package com.rath.spring.advanced.config.springaop.part3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * PART 3 - Spring AOP
 * Configuration class with @EnableAspectJAutoProxy
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.rath.spring.advanced.config.springaop.part3")
public class Part3Config {
}


