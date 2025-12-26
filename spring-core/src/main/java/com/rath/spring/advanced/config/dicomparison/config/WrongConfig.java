package com.rath.spring.advanced.config.dicomparison.config;

import com.rath.spring.advanced.config.dicomparison.SingletonTestBean;
import com.rath.spring.advanced.config.dicomparison.service.PaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "com.rath.spring.advanced.config.dicomparison")
public class WrongConfig {
    
    private static SingletonTestBean instanceUsedInWrongDependentBean;
    
    @Bean
    public PaymentGateway testPaymentGateway() {
        return new PaymentGateway() {
            @Override
            public boolean processPayment(double amount, String orderId) {
                return true;
            }
            
            @Override
            public String getProviderName() {
                return "Test";
            }
        };
    }
    
    @Bean
    public SingletonTestBean wrongHelperBean() {
        return new SingletonTestBean();
    }
    
    @Bean
    public String wrongDependentBean() {
        SingletonTestBean helper = wrongHelperBean();
        instanceUsedInWrongDependentBean = helper;
        return "wrong-dependent-" + helper.getInstanceId();
    }
    
    public static SingletonTestBean getInstanceUsedInWrongDependentBean() {
        return instanceUsedInWrongDependentBean;
    }
    
    public static void reset() {
        instanceUsedInWrongDependentBean = null;
    }
}
