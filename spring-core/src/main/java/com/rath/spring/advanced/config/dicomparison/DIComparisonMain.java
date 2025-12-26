package com.rath.spring.advanced.config.dicomparison;

import com.rath.spring.advanced.config.dicomparison.config.AppConfig;
import com.rath.spring.advanced.config.dicomparison.config.WrongConfig;
import com.rath.spring.advanced.config.dicomparison.controller.OrderController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DIComparisonMain {
    
    public static void main(String[] args) {
//        testConfigurationSingleton();
//        testComponentSingleton();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderController controller =  context.getBean(OrderController.class);
        controller.processOrder("1",45000.0d);
    }
    
    private static void testConfigurationSingleton() {
        AppConfig.reset();
        
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        SingletonTestBean helperFromContainer = context.getBean("helperBean", SingletonTestBean.class);
        SingletonTestBean instanceUsedInDependent = AppConfig.getInstanceUsedInDependentBean();
        
        boolean isSameInstance = helperFromContainer == instanceUsedInDependent;
        
        System.out.println("=== @Configuration Singleton Test ===");
        System.out.println("helperFromContainer instanceId: " + helperFromContainer.getInstanceId());
        System.out.println("instanceUsedInDependent instanceId: " + instanceUsedInDependent.getInstanceId());
        System.out.println("Same instance? " + isSameInstance);
        System.out.println("Expected: true (proxy returns singleton)");
        System.out.println("Result: " + (isSameInstance ? "PASS - Singleton works!" : "FAIL - Different instances!"));
        System.out.println();
    }
    
    private static void testComponentSingleton() {
        WrongConfig.reset();
        
        ApplicationContext wrongContext = new AnnotationConfigApplicationContext(WrongConfig.class);
        
        SingletonTestBean wrongHelperFromContainer = wrongContext.getBean("wrongHelperBean", SingletonTestBean.class);
        SingletonTestBean instanceUsedInWrongDependent = WrongConfig.getInstanceUsedInWrongDependentBean();
        
        boolean isSameInstance = wrongHelperFromContainer == instanceUsedInWrongDependent;
        
        System.out.println("=== @Component Singleton Test ===");
        System.out.println("wrongHelperFromContainer instanceId: " + wrongHelperFromContainer.getInstanceId());
        System.out.println("instanceUsedInWrongDependent instanceId: " + instanceUsedInWrongDependent.getInstanceId());
        System.out.println("Same instance? " + isSameInstance);
        System.out.println("Expected: false (direct call creates new instance)");
        System.out.println("Result: " + (!isSameInstance ? "PASS - Bug detected!" : "FAIL - Unexpectedly same!"));
        System.out.println();
        
        if (!isSameInstance) {
            System.out.println("BUG CONFIRMED: @Component with @Bean creates multiple instances!");
            System.out.println("When wrongDependentBean() calls wrongHelperBean() directly,");
            System.out.println("it creates a NEW instance instead of using the singleton bean proxy from container.");
            System.out.println("This is why @Configuration is needed - it proxies @Bean methods.");
        }
    }
}
