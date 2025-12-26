package com.rath.spring.advanced.config.dicomparison.service;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {
    
    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("StripePaymentGateway: Processing payment of $" + amount + " for order " + orderId);
        return true;
    }
    
    @Override
    public String getProviderName() {
        return "Stripe";
    }
}

