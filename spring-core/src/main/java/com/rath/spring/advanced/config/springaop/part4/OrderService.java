package com.rath.spring.advanced.config.springaop.part4;

import org.springframework.stereotype.Component;

/**
 * PART 4 - Spring Proxy Type
 * Component class (no interface) - will use CGLIB by default
 */
@Component
public class OrderService {
    
    public void processOrder(String orderId) {
        System.out.println("  [OrderService] Processing order: " + orderId);
    }
}


