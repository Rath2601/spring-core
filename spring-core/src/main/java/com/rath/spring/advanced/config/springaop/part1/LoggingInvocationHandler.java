package com.rath.spring.advanced.config.springaop.part1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * PART 1 - Pure Java Proxy
 * JDK Dynamic Proxy using InvocationHandler
 */
public class LoggingInvocationHandler implements InvocationHandler {
    
    private final Object target;
    
    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Skip logging for common JVM methods (toString, hashCode, equals, etc.)
        String methodName = method.getName();
        if (methodName.equals("toString") || methodName.equals("hashCode") || 
            methodName.equals("equals") || methodName.equals("getClass")) {
            return method.invoke(target, args);
        }
        
        System.out.println("  [PROXY] INTERCEPTED: " + methodName + "()");
        Object result = method.invoke(target, args);
        System.out.println("  [PROXY] Completed: " + methodName + "() = " + result);
        return result;
    }
}

