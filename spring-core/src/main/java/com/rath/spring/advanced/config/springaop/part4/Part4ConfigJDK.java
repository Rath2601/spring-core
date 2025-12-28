package com.rath.spring.advanced.config.springaop.part4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * PART 4 - Spring Proxy Type
 * Configuration with default proxy settings (JDK for interfaces, CGLIB for classes)
 */
@Configuration
@EnableAspectJAutoProxy // proxyTargetClass = false (default)
@ComponentScan(basePackages = "com.rath.spring.advanced.config.springaop.part4")
public class Part4ConfigJDK {
}


