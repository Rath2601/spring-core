package com.rath.spring.advanced.config.springaop.part1;

import java.lang.reflect.Proxy;

/**
 * PART 1 - Pure Java Proxy Test
 * Demonstrates:
 * 1. Method interception works
 * 2. Self-invocation behavior (method calling another method inside same class)
 */
public class Part1Test {
    
    public static void main(String[] args) {
        // Create concrete implementation
        CalculatorImpl target = new CalculatorImpl();
        System.out.println("\n[1] Direct call (NO PROXY):");
        System.out.println("    Class: " + target.getClass().getName());
        target.add(5, 3);

        // Create JDK Dynamic Proxy
        Calculator proxy = (Calculator) Proxy.newProxyInstance(
            Calculator.class.getClassLoader(),
            new Class[]{Calculator.class},
            new LoggingInvocationHandler(new CalculatorImplClone())
        );
        
        System.out.println("\n[2] External call through PROXY:");
        System.out.println("    proxy.add(10, 20)");
        proxy.add(10, 20);
        
        System.out.println("\n[3] Self-invocation test:");
        System.out.println("    proxy.multiply(4, 5) - this internally calls add()");
        System.out.println("    Expected: multiply() intercepted, but internal add() NOT intercepted");
        proxy.multiply(4, 5);
        System.out.println("    ✓ multiply() was intercepted (see [PROXY] above)");
        System.out.println("    ✗ internal add() was NOT intercepted (no [PROXY] for add())");
    }
}

