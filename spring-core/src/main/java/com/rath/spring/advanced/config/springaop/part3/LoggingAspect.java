package com.rath.spring.advanced.config.springaop.part3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * PART 3 - Spring AOP
 * Simple @Aspect with @Before advice
 * Applied to validateUser method
 */
@Aspect
@Component
public class LoggingAspect {
    
    @Before("execution(* com.rath.spring.advanced.config.springaop.part3.UserService.validateUser(..))")
    public void beforeValidateUser(JoinPoint joinPoint) {
        System.out.println("  [ASPECT] @Before advice triggered!");
        System.out.println("  [ASPECT] Method: " + joinPoint.getSignature().getName());
        System.out.println("  [ASPECT] Args: " + java.util.Arrays.toString(joinPoint.getArgs()));
    }
}


