package com.rath.spring.advanced.config.springaop.part4;

import org.springframework.stereotype.Component;

/**
 * PART 4 - Spring Proxy Type
 * Implementation with interface - will use JDK proxy by default
 */
@Component
public class ProductServiceImpl implements ProductService {
    
    @Override
    public void getProduct(String productId) {
        System.out.println("  [ProductServiceImpl] Getting product: " + productId);
    }
}


