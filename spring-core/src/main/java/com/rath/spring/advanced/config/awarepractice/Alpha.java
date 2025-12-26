package com.rath.spring.advanced.config.awarepractice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;

import java.util.Arrays;
import java.util.Locale;

public class Alpha implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        EnvironmentAware, ResourceLoaderAware, MessageSourceAware {

    private final String name;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;
    private Environment environment;
    private ResourceLoader resourceLoader;
    private MessageSource messageSource;

    Alpha(String name) {
        System.out.printf("[%s] constructor called%n", name);
        this.name = name;
    }

    // spring container provide bean name using BeanNameAware
    // bean to know its own identity
    @Override
    public void setBeanName(String beanName) {
        System.out.printf("[%s] BeanNameAware -> %s%n", name, beanName);
    }

    // access to underlying bean factory for programmatic lookups
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.printf("[%s] BeanFactoryAware%n", name);
    }

    // full application context (events, resources, parent/child info)
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.printf("[%s] ApplicationContextAware -> %s%n", name, applicationContext.getId());
    }

    // read environment properties and active profiles
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        System.out.printf("[%s] EnvironmentAware%n", name);
    }

    // load files/classpath resources
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        System.out.printf("[%s] ResourceLoaderAware%n", name);
    }

    // resolve messages (i18n)
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        System.out.printf("[%s] MessageSourceAware%n", name);
    }

    // runs after aware callbacks and dependency injection
    @PostConstruct
    public void logAwareDetails() {
        System.out.printf("[%s] BeanFactory has appConfig: %s%n", name, beanFactory.containsBean("appConfig"));
        System.out.printf("[%s] Active profiles: %s%n", name, Arrays.toString(environment.getActiveProfiles()));
        System.out.printf("[%s] java.version: %s%n", name, environment.getProperty("java.version"));
        System.out.printf("[%s] Resource exists: %s%n", name,
                resourceLoader.getResource("classpath:com/rath/spring/advanced/config/awarepractice/AppConfig.class").exists());
        String message = messageSource.getMessage(
                "aware.greeting",
                new Object[]{name},
                "Hello from MessageSource to {0}",
                Locale.getDefault()
        );
        System.out.printf("[%s] MessageSource -> %s%n", name, message);
    }

    @Override
    public String toString() {
        return name;
    }
}
