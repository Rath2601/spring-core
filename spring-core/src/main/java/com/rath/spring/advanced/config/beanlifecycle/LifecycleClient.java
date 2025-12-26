package com.rath.spring.advanced.config.beanlifecycle;

import org.springframework.stereotype.Component;

// simple client to call lifecycle bean
@Component
public class LifecycleClient {

    private final LifecycleBean lifecycleBean;

    public LifecycleClient(LifecycleBean lifecycleBean) {
        OrderLogger.log("LifecycleClient: constructor injection (demonstrates dependency wiring)");
        this.lifecycleBean = lifecycleBean;
    }

    public void invoke() {
        OrderLogger.log("LifecycleClient: invoking lifecycleBean.run() (bean ready for use)");
        lifecycleBean.run();
    }
}

