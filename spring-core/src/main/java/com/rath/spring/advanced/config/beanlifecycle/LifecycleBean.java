package com.rath.spring.advanced.config.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

// demo bean to show lifecycle callbacks
public class LifecycleBean implements InitializingBean, DisposableBean, SmartInitializingSingleton {

    public LifecycleBean() {
        OrderLogger.log("LifecycleBean: constructor -> instantiation");
    }

    @PostConstruct
    public void postConstruct() {
        OrderLogger.log("LifecycleBean: @PostConstruct -> initialization callback");
    }

    @Override
    public void afterPropertiesSet() {
        OrderLogger.log("LifecycleBean: InitializingBean.afterPropertiesSet (validate/setup after DI)");
    }

    public void customInit() {
        OrderLogger.log("LifecycleBean: custom init-method (init-method from @Bean)");
    }

    @Override
    public void afterSingletonsInstantiated() {
        OrderLogger.log("LifecycleBean: SmartInitializingSingleton.afterSingletonsInstantiated (all singletons ready)");
    }

    public void run() {
        OrderLogger.log("LifecycleBean: ready for business logic (post-initialization use)");
    }

    @PreDestroy
    public void preDestroy() {
        OrderLogger.log("LifecycleBean: @PreDestroy (annotation-based shutdown hook)");
    }

    @Override
    public void destroy() {
        OrderLogger.log("LifecycleBean: DisposableBean.destroy (cleanup via interface)");
    }

    public void customDestroy() {
        OrderLogger.log("LifecycleBean: custom destroy-method (destroy-method from @Bean)");
    }
}

