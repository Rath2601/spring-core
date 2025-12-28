package com.rath.spring.advanced.config.springaop.part4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * PART 4 - Spring Proxy Type
 * Configuration with proxyTargetClass = true (forces CGLIB for all)
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) // Force CGLIB
@ComponentScan(basePackages = "com.rath.spring.advanced.config.springaop.part4")
public class Part4ConfigCGLIB {
}


