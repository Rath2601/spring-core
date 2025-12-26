package com.rath.spring.advanced.config.beanlifecycle;

// XML-defined bean to show mixed configuration
public class XmlGreeter {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void sayHi() {
        OrderLogger.log("XmlGreeter: " + message);
    }
}

