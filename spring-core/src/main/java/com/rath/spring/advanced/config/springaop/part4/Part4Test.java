package com.rath.spring.advanced.config.springaop.part4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * PART 4 - Spring Proxy Type Test
 * Shows:
 * 1. JDK proxy vs CGLIB proxy
 * 2. Option to force CGLIB using proxyTargetClass = true
 */
public class Part4Test {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("PART 4: Spring Proxy Type");
        System.out.println("=".repeat(60));
        
        // Test 1: Default behavior (JDK for interfaces, CGLIB for classes)
        System.out.println("\n[TEST 1] Default proxy behavior:");
        System.out.println("-".repeat(60));
        
        AnnotationConfigApplicationContext context1 = 
            new AnnotationConfigApplicationContext(Part4ConfigJDK.class);
        
        ProductService productService = context1.getBean(ProductService.class);
        OrderService orderService = context1.getBean(OrderService.class);
        
        System.out.println("\n[1a] ProductService (has interface):");
        System.out.println("     Class: " + productService.getClass().getName());
        System.out.println("     Is JDK Proxy? " + java.lang.reflect.Proxy.isProxyClass(productService.getClass()));
        System.out.println("     Is CGLIB Proxy? " + productService.getClass().getName().contains("$$"));
        productService.getProduct("P123");
        
        System.out.println("\n[1b] OrderService (no interface, concrete class):");
        System.out.println("     Class: " + orderService.getClass().getName());
        System.out.println("     Is JDK Proxy? " + java.lang.reflect.Proxy.isProxyClass(orderService.getClass()));
        System.out.println("     Is CGLIB Proxy? " + orderService.getClass().getName().contains("$$"));
        orderService.processOrder("O456");
        
        context1.close();
        
        // Test 2: Force CGLIB with proxyTargetClass = true
        System.out.println("\n\n[TEST 2] Force CGLIB (proxyTargetClass = true):");
        System.out.println("-".repeat(60));
        
        AnnotationConfigApplicationContext context2 = 
            new AnnotationConfigApplicationContext(Part4ConfigCGLIB.class);
        
        ProductService productService2 = context2.getBean(ProductService.class);
        OrderService orderService2 = context2.getBean(OrderService.class);
        
        System.out.println("\n[2a] ProductService (has interface, but forced to CGLIB):");
        System.out.println("     Class: " + productService2.getClass().getName());
        System.out.println("     Is JDK Proxy? " + java.lang.reflect.Proxy.isProxyClass(productService2.getClass()));
        System.out.println("     Is CGLIB Proxy? " + productService2.getClass().getName().contains("$$"));
        productService2.getProduct("P789");
        
        System.out.println("\n[2b] OrderService (no interface, CGLIB as before):");
        System.out.println("     Class: " + orderService2.getClass().getName());
        System.out.println("     Is JDK Proxy? " + java.lang.reflect.Proxy.isProxyClass(orderService2.getClass()));
        System.out.println("     Is CGLIB Proxy? " + orderService2.getClass().getName().contains("$$"));
        orderService2.processOrder("O999");
        
        context2.close();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("OBSERVATION:");
        System.out.println("  - Default: Interface -> JDK Proxy, Class -> CGLIB Proxy");
        System.out.println("  - proxyTargetClass=true: Everything -> CGLIB Proxy");
        System.out.println("  - JDK Proxy class names: $Proxy...");
        System.out.println("  - CGLIB Proxy class names: ...$$EnhancerBySpringCGLIB$$...");
        System.out.println("=".repeat(60));
    }
}


