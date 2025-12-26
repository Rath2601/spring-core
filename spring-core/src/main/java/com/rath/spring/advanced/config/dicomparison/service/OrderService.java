package com.rath.spring.advanced.config.dicomparison.service;

import com.rath.spring.advanced.config.dicomparison.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final PaymentGateway paymentGateway;
    
    public OrderService(OrderRepository orderRepository,
                       PaymentGateway paymentGateway) {
        this.orderRepository = orderRepository;
        this.paymentGateway = paymentGateway;
    }
    
    public void processOrder(String orderId, double amount) {
        orderRepository.save(orderId);
        paymentGateway.processPayment(amount, orderId);
    }
    
    public String getPaymentProvider() {
        return paymentGateway.getProviderName();
    }
}
