package com.rath.spring.advanced.config.springaop.part2;

import org.springframework.stereotype.Component;

/**
 * PART 2 - Spring without AOP
 * Simple @Component bean with two methods where one calls the other
 */
@Component
public class SimpleService {
    
    public void methodOne() {
        System.out.println("  [SimpleService] methodOne() executed");
        System.out.println("  [SimpleService] About to call methodTwo() from within methodOne()...");
        methodTwo(); // Self-invocation
    }
    
    public void methodTwo() {
        System.out.println("  [SimpleService] methodTwo() executed");
    }
}


