package com.rath.spring.advanced.config.springaop.part2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * PART 2 - Spring without AOP Test
 * Proves no proxy is created (print class name)
 */
public class Part2Test {
    
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(Part2Config.class);

        SimpleService service = new AnnotationConfigApplicationContext(Part2Config.class).getBean(SimpleService.class);
        
        System.out.println("\n[1] Bean class name:");
        System.out.println("    " + service.getClass().getName());
        System.out.println("    Is CGLIB proxy? " + service.getClass().getName().contains("$$"));
        System.out.println("    Is JDK proxy? " + java.lang.reflect.Proxy.isProxyClass(service.getClass()));
        
        System.out.println("\n[2] External call to methodOne():");
        service.methodOne();
        
        System.out.println("\n[3] Direct call to methodTwo():");
        service.methodTwo();

        context.close();
    }
}


