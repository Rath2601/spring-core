package com.rath.spring.advanced.config.springaop;

/**
 * Master test runner - runs all parts sequentially
 * Run this to see all proxy behavior demonstrations
 */
public class ProxyBehaviorDemo {
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     Spring Proxy Behavior Demonstration - All Parts       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println("\n");
        
        // PART 1
        com.rath.spring.advanced.config.springaop.part1.Part1Test.main(args);
        
        System.out.println("\n\n");
        
        // PART 2
        com.rath.spring.advanced.config.springaop.part2.Part2Test.main(args);
        
        System.out.println("\n\n");
        
        // PART 3
        com.rath.spring.advanced.config.springaop.part3.Part3Test.main(args);
        
        System.out.println("\n\n");
        
        // PART 4
        com.rath.spring.advanced.config.springaop.part4.Part4Test.main(args);
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              All demonstrations completed!                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}


