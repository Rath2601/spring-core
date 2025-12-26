package com.rath.spring.advanced.config.dicomparison.service;

public interface PaymentGateway {
    boolean processPayment(double amount, String orderId);
    String getProviderName();
}

