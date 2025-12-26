package com.rath.spring.advanced.config.dicomparison.config;

import com.rath.spring.advanced.config.dicomparison.SingletonTestBean;
import com.rath.spring.advanced.config.dicomparison.service.PaymentGateway;
import com.rath.spring.advanced.config.dicomparison.service.StripePaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class AppConfig {
    
    private static SingletonTestBean instanceUsedInDependentBean;
    
    @Bean
    @Primary
    public PaymentGateway stripePaymentGateway() {
        return new StripePaymentGateway();
    }
    
    @Bean
    public PaymentGateway paypalPaymentGateway() {
        return new PaymentGateway() {
            @Override
            public boolean processPayment(double amount, String orderId) {
                return true;
            }
            
            @Override
            public String getProviderName() {
                return "PayPal";
            }
        };
    }
    
    @Bean
    public SingletonTestBean helperBean() {
        return new SingletonTestBean();
    }
    
    @Bean
    public String dependentBean() {
        SingletonTestBean helper = helperBean();
        instanceUsedInDependentBean = helper;
        return "dependent-" + helper.getInstanceId();
    }
    
    public static SingletonTestBean getInstanceUsedInDependentBean() {
        return instanceUsedInDependentBean;
    }
    
    public static void reset() {
        instanceUsedInDependentBean = null;
    }
}
