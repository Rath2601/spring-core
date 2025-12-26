package com.rath.spring.advanced.config.dicomparison.service;

import org.springframework.stereotype.Component;

@Component
public class PayPalPaymentGateway implements PaymentGateway {
    
    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("PayPalPaymentGateway: Processing payment of $" + amount + " for order " + orderId);
        return true;
    }
    
    @Override
    public String getProviderName() {
        return "PayPal";
    }
}

