package com.rath.spring.advanced.config.beanlifecycle;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecycleMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("classpath:beanlifecycle/extra-beans.xml");
        context.register(LifecycleConfig.class);
        context.refresh();

        context.getBean(LifecycleClient.class).invoke();
        context.getBean(XmlGreeter.class).sayHi();

        context.registerShutdownHook();
    }
}

