package com.rath.spring.advanced.config.springaop.part1;

/**
 * PART 1 - Pure Java Proxy
 * Concrete implementation
 */
public class CalculatorImpl implements Calculator {
    
    @Override
    public int add(int a, int b) {
        System.out.println("    [CalculatorImpl] add(" + a + ", " + b + ")");
        return a + b;
    }
    
    @Override
    public int multiply(int a, int b) {
        System.out.println("    [CalculatorImpl] multiply(" + a + ", " + b + ")");
        System.out.println("    [CalculatorImpl] -> calling add() internally (self-invocation)");
        int sum = add(a, b); // Self-invocation: NOT through proxy
        System.out.println("    [CalculatorImpl] -> add() completed (bypassed proxy)");
        return a * b;
    }
}

