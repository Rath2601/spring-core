package com.rath.spring.advanced.config.springaop.part1;

public class CalculatorImplClone implements Calculator{
    @Override
    public int add(int a, int b) {
        System.out.println("    [CalculatorImplClone] add(" + a + ", " + b + ")");
        return a + b;
    }

    @Override
    public int multiply(int a, int b) {
        System.out.println("    [CalculatorImplClone] multiply(" + a + ", " + b + ")");
        return a * b;
    }
}
