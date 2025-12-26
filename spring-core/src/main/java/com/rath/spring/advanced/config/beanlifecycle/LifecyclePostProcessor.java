package com.rath.spring.advanced.config.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

// logs factory metadata and bean init hooks
public class LifecyclePostProcessor implements BeanFactoryPostProcessor, BeanPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String names = String.join(", ", beanFactory.getBeanDefinitionNames());
        OrderLogger.log("BeanFactoryPostProcessor: adjust metadata before instantiation -> count="
                + beanFactory.getBeanDefinitionCount() + " names=[" + names + "]");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (isUserBean(bean)) {
            OrderLogger.log("BeanPostProcessor before init (modify instances pre-init) -> " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (isUserBean(bean)) {
            OrderLogger.log("BeanPostProcessor after init (wrapping/proxy point) -> " + beanName);
        }
        return bean;
    }

    private boolean isUserBean(Object bean) {
        return bean.getClass().getPackageName().startsWith("com.rath.spring.advanced.config.beanlifecycle");
    }
}

