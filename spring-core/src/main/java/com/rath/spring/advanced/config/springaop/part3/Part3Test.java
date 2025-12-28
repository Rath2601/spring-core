package com.rath.spring.advanced.config.springaop.part3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * PART 3 - Spring AOP Test
 * Shows:
 * 1. External method call is intercepted
 * 2. Self-invocation is NOT intercepted
 */
public class Part3Test {
    
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(Part3Config.class);
        
        UserService service = context.getBean(UserService.class);
        
        System.out.println("\n[1] Bean class name:");
        System.out.println("    " + service.getClass().getName());
        System.out.println("    Is CGLIB proxy? " + service.getClass().getName().contains("$$"));
        System.out.println("    Is JDK proxy? " + java.lang.reflect.Proxy.isProxyClass(service.getClass()));
        
        System.out.println("\n[2] External call to validateUser() (SHOULD BE INTERCEPTED):");
        service.validateUser("john");
        
        System.out.println("\n[3] External call to createUser() which internally calls validateUser()");
        System.out.println("    (Self-invocation - validateUser() should NOT be intercepted):");
        service.createUser("jane");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("OBSERVATION:");
        System.out.println("  - External call (service.validateUser) -> INTERCEPTED by @Aspect");
        System.out.println("  - Self-invocation (createUser -> validateUser) -> NOT intercepted");
        System.out.println("  - Reason: Spring AOP uses proxy, self-invocation uses 'this', not proxy");
        System.out.println("=".repeat(60));
        
        context.close();
    }
}


