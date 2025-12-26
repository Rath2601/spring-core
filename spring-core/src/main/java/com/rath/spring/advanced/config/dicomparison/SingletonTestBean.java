package com.rath.spring.advanced.config.dicomparison;

public class SingletonTestBean {
    private final String instanceId;
    
    public SingletonTestBean() {
        this.instanceId = "instance-" + System.identityHashCode(this);
    }
    
    public String getInstanceId() {
        return instanceId;
    }
}
