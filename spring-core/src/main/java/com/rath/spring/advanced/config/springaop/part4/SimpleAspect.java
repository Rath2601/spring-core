package com.rath.spring.advanced.config.springaop.part4;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * PART 4 - Spring Proxy Type
 * Simple aspect to trigger proxy creation
 */
@Aspect
@Component
public class SimpleAspect {
    
    @Before("execution(* com.rath.spring.advanced.config.springaop.part4.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("  [ASPECT] Before: " + joinPoint.getSignature().getName());
    }
}


